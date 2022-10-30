package com.example.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.data.dao.MemberRepository;
import com.example.data.dto.req.AuthDto;
import com.example.data.po.MemberPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;


@Slf4j
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Mono<Boolean> signup(AuthDto authDto) {
        return memberRepository
                .findByUserId(authDto.getUserId())
                .flatMap(memberPo -> Mono.just(false))
                .switchIfEmpty(
                        memberRepository
                                .save(
                                        MemberPo
                                                .builder()
                                                .id(null)
                                                .userId(authDto.getUserId())
                                                .passwordHash(BCrypt.withDefaults().hashToString(12, authDto.getPassword().toCharArray()))
                                                .createDate(Instant.now().toEpochMilli())
                                                .build()
                                ).map(memberPo -> true)
                );
    }
}
