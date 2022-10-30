package com.example.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.config.Constant;
import com.example.data.dao.MemberRepository;
import com.example.data.dao.MessageRepository;
import com.example.data.dto.Protocol;
import com.example.data.enu.Action;
import com.example.data.po.MessagePo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
public class MessageService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Cache<String, String> remoteAddressToUserId =
            Caffeine
                    .newBuilder()
                    .initialCapacity(1024)
                    .build();
    private final MemberRepository memberRepository;
    private final MessageRepository messageRepository;

    public MessageService(MemberRepository memberRepository, MessageRepository messageRepository) {
        this.memberRepository = memberRepository;
        this.messageRepository = messageRepository;
    }

    @Scheduled(cron = "*/15 * * * * *")
    private void heartBeat() {
        userIdToSession
                .asMap()
                .forEach((userId, session) -> {
                            try {
                                session.write(Buffer.buffer(String.format("%s%s", objectMapper.writeValueAsString(Protocol.builder().action(Action.HEARTBEAT).build()), Constant.CR)));
                            } catch (Exception ex) {
                                log.error(ex.getMessage(), ex);
                            }
                        }
                );
    }

    public void handleMsg(ServerWebSocket session, String message) {
        try {
            log.info(String.format("Server received -> %s", message));
            final Protocol protocol = objectMapper.readValue(message, Protocol.class);

            if (protocol.getAction() == Action.ACK_HEARTBEAT) {
                handleAckHeartBeat(session);
            } else if (protocol.getAction() == Action.AUTH) {
                handleAuthSession(protocol, session);
            } else if (protocol.getAction() == Action.CMD) {
                handleCmd(protocol, session);
            } else {
                throw new RuntimeException(String.format("Unsupported Action %s, We will close this session !", protocol.getAction()));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            cleanSession(session, "Handle Msg Exception");
        }
    }

    private void handleAckHeartBeat(ServerWebSocket session) {
        accessForPreventingExpire(session);
    }

    private void handleAuthSession(Protocol protocol, ServerWebSocket session) {
        memberRepository
                .findByUserId(protocol.getUserId())
                .mapNotNull(memberPo -> {
                    if (memberPo != null &&
                            BCrypt.verifyer().verify(protocol.getContent().toCharArray(), memberPo.getPasswordHash().toCharArray()).verified
                    ) {
                        userIdToSession.put(protocol.getUserId(), session);
                        remoteAddressToUserId.put(session.remoteAddress().toString(), protocol.getUserId());
                        try {
                            session.write(Buffer.buffer(String.format("%s%s", objectMapper.writeValueAsString(Protocol.builder().action(Action.ACK_AUTH).userId(protocol.getUserId()).build()), Constant.CR)));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        cleanSession(session, "Wrong UserId / Password Mapping");
                    }
                    return null;
                })
                .publishOn(Schedulers.boundedElastic())
                .subscribe();
    }

    private void handleCmd(Protocol protocol, ServerWebSocket session) {
        accessForPreventingExpire(session);

        messageRepository
                .save(
                        MessagePo
                                .builder()
                                .action(protocol.getAction().name())
                                .userId(protocol.getUserId())
                                .content(protocol.getContent())
                                .createDate(Instant.now().toEpochMilli())
                                .build()
                );
    }

    public void cleanSession(ServerWebSocket session, String reason) {
        log.info(String.format("Close session -> %s, because %s", remoteAddressToUserId.getIfPresent(session.remoteAddress().toString()), reason));

        session.close();
        Optional
                .ofNullable(remoteAddressToUserId.getIfPresent(session.remoteAddress().toString()))
                .ifPresent(userIdToSession::invalidate);
        remoteAddressToUserId.invalidate(session.remoteAddress().toString());
    }

    private void accessForPreventingExpire(ServerWebSocket session) {
        Optional
                .ofNullable(remoteAddressToUserId.getIfPresent(session.remoteAddress().toString()))
                .ifPresent(userIdToSession::getIfPresent);
    }

    private final Cache<String, ServerWebSocket> userIdToSession =
            Caffeine
                    .newBuilder()
                    .initialCapacity(1024)
                    .expireAfterAccess(Duration.ofSeconds(35))
                    .evictionListener((RemovalListener<String, ServerWebSocket>) (userId, session, removalCause) -> {
                        if (null != session) {
                            cleanSession(session, "Evict");
                        }
                    })
                    .build();


}
