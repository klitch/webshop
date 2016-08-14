package service;

import entity.User;

/**
 * Created by Vitalii_Bandura on 5/6/2015.
 */
public interface UserService {
    boolean addUser(User user);
    User getUser(String email);
    boolean isUserExists(String email);
    boolean checkPassword(String email, String password);
}
