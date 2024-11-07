package UTN.FRC.sistemas.Gateway.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class jwtUtils {

    @Value("${}")
    private String secret;

    @Value("${}")
    private int expirationMillis;

    public String generateToken(UserDetails userDetails){

    }

    public boolean validateToke(String token){

    }

    public String extractUsernameFromToken(String token){

    }
}
