package com.karakoc.starterproject.accounts;


import com.karakoc.starterproject.security.JWTService;
import com.karakoc.starterproject.user.UserDTO;
import com.karakoc.starterproject.user.requests.LoginRequest;
import com.karakoc.starterproject.user.requests.RegisterRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts Controller")
@AllArgsConstructor
public class AccountController {
    private final AccountService service;
    private final JWTService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest r) {
        return service.login(r);
    }

    @PostMapping("/register")
    public UserDTO register(@RequestBody  RegisterRequest r) {
        return service.register(r);
    }


    @PostMapping("/verification/token")
    public boolean verificateToken(@RequestBody String token){
        jwtService.validateToken(token.substring(10,token.length()-2));
        return true;
    }

}
