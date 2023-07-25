package tw.wsa.gaas.java.spring.config.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernamePasswordPairDTO {

    private String username;
    private String password;
}
