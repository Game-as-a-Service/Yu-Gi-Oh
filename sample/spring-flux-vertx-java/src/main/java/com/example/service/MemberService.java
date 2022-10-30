package com.example.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.data.dao.MemberRepository;
import com.example.data.dto.req.AuthDto;
import com.example.data.po.MemberPo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Mono<Boolean> signup(AuthDto authDto) {
        return memberRepository
                .findByUserId(authDto.getUserId())
                .flatMap(memberPo -> {
                    if (memberPo != null) {
                        return Mono.just(false);
                    } else {
                        return memberRepository
                                .save(
                                        MemberPo
                                                .builder()
                                                .userId(authDto.getUserId())
                                                .passwordHash(BCrypt.withDefaults().hashToString(12, authDto.getPassword().toCharArray()))
                                                .build()
                                )
                                .map(save -> true);
                    }
                });
    }
}
