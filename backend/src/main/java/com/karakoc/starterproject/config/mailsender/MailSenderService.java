package com.karakoc.starterproject.config.mailsender;

public interface MailSenderService {

    String forgotPassword(String username);
    void errorRequest(String mailAdress);
}
