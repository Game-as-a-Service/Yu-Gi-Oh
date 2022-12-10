package tw.gaas.yugioh.web.security;

import net.purefunc.emoji.Emoji3;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class SpringUserDetailsService implements UserDetailsService {

    private final Map<String, User> userMap;

    public SpringUserDetailsService() {
        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userMap = new HashMap<>() {{
            put("vincent", new User("vincent", passwordEncoder.encode("123456"), List.of(new SimpleGrantedAuthority("ROLE_USER"))));
            put("qrtt1", new User("qrtt1", passwordEncoder.encode("123456"), List.of(new SimpleGrantedAuthority("ROLE_USER"))));
            put("musk", new User("musk", passwordEncoder.encode("123456"), List.of(new SimpleGrantedAuthority("ROLE_USER"))));
        }};
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional
                .of(userMap.get(username))
                .orElseThrow(() -> new UsernameNotFoundException(Emoji3.DOUBLE_EXCLAMATION_MARK + " " + username + " not found"));
    }
}