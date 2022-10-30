package com.example.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.data.dao.MemberRepository;
import com.example.data.dto.req.AuthDto;
import com.example.data.po.MemberPo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;


@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Boolean signup(AuthDto authDto) {
        final Optional<MemberPo> memberPoOpt = memberRepository.findByUserId(authDto.getUserId());

        if (memberPoOpt.isPresent()) {
            return false;
        } else {
            memberRepository
                    .save(
                            MemberPo
                                    .builder()
                                    .userId(authDto.getUserId())
                                    .passwordHash(BCrypt.withDefaults().hashToString(12, authDto.getPassword().toCharArray()))
                                    .createDate(Instant.now().toEpochMilli())
                                    .build()
                    );
            return true;
        }
    }
}
