package tw.wsa.gaas.java.spring.config.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernamePasswordPairDTO {

    private String username;
    private String password;
}