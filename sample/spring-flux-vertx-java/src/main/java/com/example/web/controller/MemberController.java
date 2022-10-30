package com.example.web.controller;

import com.example.data.dto.req.AuthDto;
import com.example.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/v1.0/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/auth")
    Mono<ResponseEntity<Void>> signup(@RequestBody AuthDto authDto) {
        return memberService
                .signup(authDto)
                .map(signUpResult -> {
                    if (signUpResult) {
                        return ResponseEntity.created(URI.create("")).build();
                    } else {
                        return ResponseEntity.badRequest().build();
                    }
                });
    }
}
