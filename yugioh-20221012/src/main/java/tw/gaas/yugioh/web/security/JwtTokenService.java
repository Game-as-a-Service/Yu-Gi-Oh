package tw.gaas.yugioh.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenService {

    private final String key = "HOW_DO_YOU_TURN_THIS_ON_HOW_DO_YOU_TURN_THIS_ON_HOW_DO_YOU_TURN_THIS_ON";

    public String generate(String subject, String uuid) {
        final Date now = new Date();
        return Jwts.builder()
                .setIssuer("Yu-Yi-Oh")
                .setSubject(subject)
                .setAudience(null)
                .setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
                .setNotBefore(now)
                .setIssuedAt(now)
                .setId(uuid)
                .signWith(Keys.hmacShaKeyFor(key.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    public String retrieveSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
