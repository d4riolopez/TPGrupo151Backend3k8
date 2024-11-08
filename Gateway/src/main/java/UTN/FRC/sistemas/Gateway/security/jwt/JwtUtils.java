package UTN.FRC.sistemas.Gateway.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${gateway.security.secret}")
    private String secret;

    @Value("${jwt.duration.millis}")
    private int expirationMillis;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationMillis))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToke(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsernameFromToken(String token) {
        return
                Jwts.parserBuilder()
                        .setSigningKey(key())
                        .build()
                        .parseClaimsJws(token).getBody().getSubject();
    }
}
