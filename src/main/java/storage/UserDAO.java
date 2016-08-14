package storage;

import entity.User;

import java.sql.Connection;

public interface UserDAO {
    boolean addUser(Connection connection, User user);
    User getUser(Connection connection, String email);
}
