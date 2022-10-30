package com.example.service;

import com.example.data.dao.MemberRepository;
import com.example.data.dto.req.AuthDto;
import com.example.data.po.MemberPo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MemberService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberRepository memberRepository;

    public MemberService(BCryptPasswordEncoder bCryptPasswordEncoder, MemberRepository memberRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
                                    .passwordHash(bCryptPasswordEncoder.encode(authDto.getPassword()))
                                    .build()
                    );
            return true;
        }
    }
}
