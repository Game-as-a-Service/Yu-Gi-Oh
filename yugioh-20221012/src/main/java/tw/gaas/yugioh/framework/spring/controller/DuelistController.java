package tw.gaas.yugioh.framework.spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.gaas.yugioh.framework.spring.security.JwtTokenService;
import tw.gaas.yugioh.framework.spring.security.LoginRequestDTO;
import tw.gaas.yugioh.framework.spring.security.LoginResponseDTO;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/java/api/v1.0")
@RequiredArgsConstructor
public class DuelistController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenService jwtTokenService;

    @PostMapping("/duelists:login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequestDTO.getUsername(),
                                loginRequestDTO.getPassword()
                        )
                );

        return ResponseEntity.ok(
                new LoginResponseDTO(
                        jwtTokenService.generate(
                                loginRequestDTO.getUsername(),
                                UUID.randomUUID().toString()
                        )
                )
        );
    }
}
