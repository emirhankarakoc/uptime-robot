package com.karakoc.starterproject.user;


public interface UserService {
    void deleteUserById(String id);

    UserDTO getUserById(String id);

    User getUserByToken(String token);

    String forgotPassword(String username);

    String changePassword(String username, String password);
    UserDTO getUserDTOByToken(String token);
}
