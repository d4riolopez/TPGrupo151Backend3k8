package UTN.FRC.sistemas.Gateway.security.configuration;

import UTN.FRC.sistemas.Gateway.security.jwt.JwtFilter;
import UTN.FRC.sistemas.Gateway.security.user.model.Role;
import UTN.FRC.sistemas.Gateway.security.user.service.UserDetailsServiceImp;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Repository;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsServiceImp service;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter getFilter() {
        return new JwtFilter();
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(getPasswordEncoder());
        return new ReactiveAuthenticationManager(provider);
    }


    //----------Configuration using MVC for security, not supported for webFLux
    /*
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public DaoAuthenticationProvider getProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(getPasswordEncoder());
        provider.setUserDetailsService(service);
        return provider;

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/api/v1/auth/**", "/api/v1/user/**").permitAll()
                        .requestMatchers("/api/v1/notification/").hasAuthority(Role.EMPLOYEE.name())
                        .requestMatchers(HttpMethod.POST,"/api/v1/TPI/test/").hasAuthority(Role.EMPLOYEE.name())
                        .requestMatchers("/api/v1/TPI/position/**").hasAuthority(Role.INTERESTED.name())
                        .requestMatchers("/api/v1/TPI/report/**").hasAuthority(Role.ADMIN.name())
                )



                .addFilterBefore(getFilter(), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(getProvider())
                .build();
    }*/
}
