package UTN.FRC.sistemas.Gateway.security.controller;

import UTN.FRC.sistemas.Gateway.security.user.model.User;
import UTN.FRC.sistemas.Gateway.security.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService service;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user){
        service.createUser(user);
        return ResponseEntity.ok("User Created successfully");
    }
}
