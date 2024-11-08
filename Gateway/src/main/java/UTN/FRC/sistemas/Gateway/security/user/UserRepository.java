package UTN.FRC.sistemas.Gateway.security.user;

import UTN.FRC.sistemas.Gateway.security.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
