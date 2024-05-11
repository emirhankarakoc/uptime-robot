package com.karakoc.starterproject.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.karakoc.starterproject.config.mailsender.MailSenderService;
import com.karakoc.starterproject.exceptions.general.NotfoundException;
import com.karakoc.starterproject.security.JWTService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.karakoc.starterproject.user.User.userToDTO;

@Service
@AllArgsConstructor
@Slf4j

public class UserManager implements UserService {
    private final UserRepository repository;
    private final JWTService jwtService;
    private final MailSenderService mailSenderService;


    public void deleteUserById(String id) {
        repository.deleteById(id);
    }

    public UserDTO getUserById(String id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return userToDTO(user.get());
        } else {
            throw new NotfoundException("User not found.");
        }
    }

    public String forgotPassword(String username) {
        return mailSenderService.forgotPassword(username);
    }

    public String changePassword(String token, String password) {
        User user = repository.findByToken(token).orElseThrow(() -> new NotfoundException("User not found"));
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        user.setPassword(hashedPassword);
        repository.save(user);
        return "success.";
    }


}
