package storage.implementation;

import entity.Role;
import entity.User;
import exception.DAOException;
import storage.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vitalii_Bandura on 4/27/2015.
 */
public class UserDAOImpl implements UserDAO {

    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    public static final String INSERT_NEW_USER = "INSERT INTO users VALUES (?,?,?,?,?,?,?)";

    public boolean addUser(Connection connection, User user) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW_USER);
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setBoolean(6, user.isAgreeForNewsletter());
            preparedStatement.setString(7, user.getPhotoName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot add user to DB", e);
        }
        return true;
    }

    public User getUser(Connection connection, String email) {
        User user = null;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getString(1));
                user.setEmail(resultSet.getString(2));
                user.setFirstName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setAgreeForNewsletter(resultSet.getBoolean(6));
                user.setPhotoName(resultSet.getString(7));
                user.setRole(Role.valueOf(resultSet.getString(8)));
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get user from DB", e);
        }
        return user;
    }
}
