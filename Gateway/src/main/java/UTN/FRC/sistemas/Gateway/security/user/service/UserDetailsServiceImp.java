package UTN.FRC.sistemas.Gateway.security.user.service;

import UTN.FRC.sistemas.Gateway.security.user.UserNotFoundException;
import UTN.FRC.sistemas.Gateway.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("There isn't an user with that username: "+ username));
    }
}
