package UTN.FRC.sistemas.Gateway.security.controller;

import UTN.FRC.sistemas.Gateway.security.jwt.JwtUtils;
import UTN.FRC.sistemas.Gateway.security.request.LogInRequest;
import UTN.FRC.sistemas.Gateway.security.user.service.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final UserDetailsServiceImp service;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> logInUser(@RequestBody LogInRequest logInRequest) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(logInRequest.username(), logInRequest.password()));
            String token = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());

            return ResponseEntity.ok(token);
        } catch (BadCredentialsException bce) {
            return new ResponseEntity<>("invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

}
