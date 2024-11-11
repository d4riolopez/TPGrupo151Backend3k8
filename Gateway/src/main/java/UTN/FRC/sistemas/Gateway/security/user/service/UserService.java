package UTN.FRC.sistemas.Gateway.security.user.service;

import UTN.FRC.sistemas.Gateway.security.user.UserRepository;
import UTN.FRC.sistemas.Gateway.security.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder encoder;
    private final UserRepository repository;

    public void createUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

}
