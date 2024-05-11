package com.karakoc.starterproject.user;


import com.karakoc.starterproject.exceptions.general.ForbiddenException;
import com.karakoc.starterproject.exceptions.general.UnauthorizatedException;
import com.karakoc.starterproject.security.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final UserRepository repository;
    private final JWTService jwtService;


    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id, @RequestParam String token) {
        onlyAdmin(token);
        service.deleteUserById(id);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable String id, @RequestParam String token) {
        isLoggedIn(token);
        return service.getUserById(id);
    }


    @PutMapping("/password/forgot")
    public String forgotPassword(@RequestParam String username) {
        return service.forgotPassword(username);
    }

    @PutMapping("password/change")
    public String changePassword(@RequestParam String token,@RequestParam String pw){
        isLoggedIn(token);
        return service.changePassword(token,pw);

    }


    private void onlyAdmin(String token) {
        jwtService.validateToken(token);
        User user = repository.findByToken(token).get();
        if (!user.getType().equals(UserType.ADMIN)) {
            throw new UnauthorizatedException("Unauthorizated.");
        }
    }

    private void isLoggedIn(String token) {
        jwtService.validateToken(token);
    }
}
