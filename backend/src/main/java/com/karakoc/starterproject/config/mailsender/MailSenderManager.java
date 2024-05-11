package com.karakoc.starterproject.config.mailsender;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.karakoc.starterproject.exceptions.general.NotfoundException;
import com.karakoc.starterproject.user.User;
import com.karakoc.starterproject.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@AllArgsConstructor
public class MailSenderManager implements MailSenderService {
    private final UserRepository repository;
    private final JavaMailSender mailSender;

    public String forgotPassword(String mail) {
        User user = repository.findByMail(mail).orElseThrow(() -> new NotfoundException("User not found."));
        Random random = new Random();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        String password = "" + random.nextDouble(9999999);
        String hashedPassword = BCrypt.withDefaults().hashToString(12, ("" + random.nextDouble(9999999)).toCharArray());
        user.setPassword(hashedPassword);
        simpleMailMessage.setFrom("shopifyemirhan6@gmail.com");
        simpleMailMessage.setSubject("FORGET PASSWORD REQUEST");
        simpleMailMessage.setTo(user.getUsername());
        simpleMailMessage.setText("Hello Mr/Mrs "+user.getUsername().toUpperCase()+ ", we got a forgot request.\nYour new password: " + password + "\n\n!!!!!...Don't forget change your password after log in with new password...!!!!!!!");
        repository.save(user);
        mailSender.send(simpleMailMessage);
        return "Mr/Mrs. " + user.getUsername().toUpperCase() + " , new password sent to your mail adress. Please check your mailbox.\n\n!!!!!...Don't forget change your password after log in with new password....!!!!!!!";

    }

}
