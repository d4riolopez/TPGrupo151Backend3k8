package UTN.FRC.sistemas.Gateway.controller;

import UTN.FRC.sistemas.Gateway.model.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
public class AuthController {
    private final ReactiveAuthenticationManager authenticationManager;
    private final ReactiveUserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secret;

    public AuthController(ReactiveAuthenticationManager authenticationManager,
                          ReactiveUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public Mono<String> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());

        return authenticationManager.authenticate(authenticationToken)
                .map(authentication -> {
                    ReactiveSecurityContextHolder.withAuthentication(authentication);
                    return generateToken(authRequest.getUsername());
                })
                .onErrorResume(e -> Mono.error(new RuntimeException("Invalid username or password")));
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 horas
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
