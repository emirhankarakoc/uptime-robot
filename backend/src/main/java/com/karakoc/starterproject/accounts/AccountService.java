package com.karakoc.starterproject.accounts;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.karakoc.starterproject.exceptions.general.BadRequestException;
import com.karakoc.starterproject.exceptions.general.NotfoundException;
import com.karakoc.starterproject.security.JWTService;
import com.karakoc.starterproject.user.User;
import com.karakoc.starterproject.user.UserDTO;
import com.karakoc.starterproject.user.UserRepository;
import com.karakoc.starterproject.user.requests.LoginRequest;
import com.karakoc.starterproject.user.requests.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.karakoc.starterproject.user.User.*;

@AllArgsConstructor
@Service
public class AccountService {

    private final UserRepository repository;
    private final JWTService jwtService;

    public String login(LoginRequest request) {
        User user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new NotfoundException("Username and password do not match."));

        if (BCrypt.verifyer().verify(request.getPassword().toCharArray(), user.getPassword()).verified) {
            user.setToken(jwtService.generateToken(user.getUsername()));
            repository.save(user);
            return user.getToken();
        } else {
            throw new NotfoundException("Username and password do not match."); // sifreler eslesmiyorsa.
        }

    }


    public UserDTO register(RegisterRequest request) {
        Optional<User> testUser = repository.findByUsername(request.getUsername());
        if (testUser.isPresent()) {
            throw new BadRequestException("You are already signed in.");
        }
        User user = createSimpleUser(request);
        updateUser(user);
        // Token oluştur ve kullanıcıyı kaydet
        user.setToken(jwtService.generateToken(user.getUsername()));
        return userToDTO(repository.save(user));
    }
}
