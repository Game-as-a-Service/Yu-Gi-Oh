package tw.gaas.yugioh.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.gaas.yugioh.web.security.JwtTokenService;
import tw.gaas.yugioh.web.security.LoginRequestDto;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/java/api/v1.0")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenService jwtTokenService;

    private final UserDetailsService springUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, UserDetailsService springUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.springUserDetailsService = springUserDetailsService;
    }

    @PostMapping("/auth:login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));

        final UserDetails userDetails = springUserDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        final String bearerToken = jwtTokenService.generate(userDetails.getUsername(), UUID.randomUUID().toString());

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, bearerToken)
                .body(null);
    }
}
