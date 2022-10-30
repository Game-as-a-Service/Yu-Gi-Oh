package com.example.web.controller;

import com.example.data.dto.req.AuthDto;
import com.example.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1.0/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/auth")
    ResponseEntity<Void> signup(@RequestBody AuthDto authDto) {
        final Boolean signupResult = memberService.signup(authDto);
        if (signupResult) {
            return ResponseEntity.created(URI.create("")).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
