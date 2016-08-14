package storage.implementation;

import entity.order.Order;
import exception.DAOException;
import storage.OrderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Виталий on 14.05.2015.
 */
public class OrderDAOImpl implements OrderDAO {
    public boolean addOrder(Connection connection, Order order) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("INSERT INTO `order` VALUES(?,?,?,?,?,?)");
            statement.setString(1, order.getId());
            statement.setString(2, order.getUserId());
            statement.setString(3, order.getCreditCard());
            statement.setString(4, order.getAddress());
            Object date = new java.sql.Timestamp(order.getDateOfCreation().getTime());
            statement.setObject(5, date);
            statement.setString(6, order.getStatus().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot add order to DB", e);
        }
        return true;
    }
}
