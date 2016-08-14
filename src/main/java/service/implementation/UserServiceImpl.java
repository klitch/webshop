package service.implementation;

import entity.User;
import service.UserService;
import storage.UserDAO;
import transaction.TransactionManager;
import transaction.TransactionOperation;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vitalii_Bandura on 5/6/2015.
 */
public class UserServiceImpl implements UserService {
    private UserDAO storage;
    private TransactionManager transactionManager;

    public UserServiceImpl(UserDAO storage, TransactionManager transactionManager) {
        this.storage = storage;
        this.transactionManager = transactionManager;
    }

    public boolean addUser(final User user) {
        return transactionManager.doTransaction(new TransactionOperation<Boolean>() {
            @Override
            public Boolean doTransaction(Connection connection) throws SQLException {
                return storage.addUser(connection, user);
            }
        });
    }

    public User getUser(final String email) {
        return transactionManager.doTransaction(new TransactionOperation<User>() {
            @Override
            public User doTransaction(Connection connection) throws SQLException {
                return storage.getUser(connection, email);
            }
        });
    }

    public boolean isUserExists(final String email){
        User user = transactionManager.doTransaction(new TransactionOperation<User>() {
            @Override
            public User doTransaction(Connection connection) throws SQLException {
                return storage.getUser(connection, email);
            }
        });
        return user != null;
    }

    public boolean checkPassword(final String email, String password){
        User user = transactionManager.doTransaction(new TransactionOperation<User>() {
            @Override
            public User doTransaction(Connection connection) throws SQLException {
                return storage.getUser(connection, email);
            }
        });
        return user.getPassword().equals(password);
    }
}
