package tw.wsa.yugioh.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalListener;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tw.wsa.yugioh.config.Constant;
import tw.wsa.yugioh.data.dao.MessageRepository;
import tw.wsa.yugioh.data.dto.Protocol;
import tw.wsa.yugioh.data.enu.Action;
import tw.wsa.yugioh.data.po.MessagePo;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Slf4j
@Service
public class MsgService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Cache<String, Long> remoteAddressToUserId = Caffeine.newBuilder()
            .initialCapacity(1024)
            .build();

    private final MessageRepository messageRepository;

    public MsgService(MessageRepository messageRepository) {
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
            log.info(message);

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

    private void accessForPreventingExpire(ServerWebSocket session) {
        Optional
                .ofNullable(remoteAddressToUserId.getIfPresent(session.remoteAddress().toString()))
                .ifPresent(userIdToSession::getIfPresent);
    }

    private void handleAuthSession(Protocol protocol, ServerWebSocket session) throws Exception {
        userIdToSession.put(protocol.getUserId(), session);
        remoteAddressToUserId.put(session.remoteAddress().toString(), protocol.getUserId());

        session.write(Buffer.buffer(String.format("%s%s", objectMapper.writeValueAsString(Protocol.builder().action(Action.ACK_AUTH).userId(protocol.getUserId()).build()), Constant.CR)));
    }

    private void handleCmd(Protocol protocol, ServerWebSocket session) {
        accessForPreventingExpire(session);

        log.info(String.format("Server Received -> %s", protocol.getContent()));
        messageRepository.save(
                MessagePo.builder()
                        .action(protocol.getAction().name())
                        .userId(protocol.getUserId())
                        .content(protocol.getContent())
                        .createDate(Instant.now().toEpochMilli())
                        .build()
        );
    }

    public void cleanSession(ServerWebSocket session, String reason) {
        log.info(String.format("Close session -> %d, because %s", remoteAddressToUserId.getIfPresent(session.remoteAddress().toString()), reason));

        session.close();
        Optional
                .ofNullable(remoteAddressToUserId.getIfPresent(session.remoteAddress().toString()))
                .ifPresent(userIdToSession::invalidate);
        remoteAddressToUserId.invalidate(session.remoteAddress().toString());
    }

    private final Cache<Long, ServerWebSocket> userIdToSession = Caffeine.newBuilder()
            .initialCapacity(1024)
            .expireAfterAccess(Duration.ofSeconds(35))
            .evictionListener((RemovalListener<Long, ServerWebSocket>) (userId, session, removalCause) -> {
                if (null != session) {
                    cleanSession(session, "Evict");
                }
            })
            .build();
}
