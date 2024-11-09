package UTN.FRC.sistemas.Gateway.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MyUserDetailsService implements ReactiveUserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public MyUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        UserDetails user = getUserDetails(username);
        if (user == null) {
            return Mono.error(new UsernameNotFoundException("User not found"));
        }
        return Mono.just(user);
    }

    private UserDetails getUserDetails(String username) {
        if (username.equals("user")) {
            return User.withUsername("user")
                    .password(passwordEncoder.encode("password"))
                    .roles("USER")
                    .build();
        } else if (username.equals("admin")) {
            return User.withUsername("admin")
                    .password(passwordEncoder.encode("adminpassword"))
                    .roles("ADMIN")
                    .build();
        } else if (username.equals("employed")) {
            return User.withUsername("employed")
                    .password(passwordEncoder.encode("employedpassword"))
                    .roles("EMPLOYED")
                    .build();
        } else {
            return null;
        }
    }
}

