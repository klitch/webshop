package storage.implementation;

import entity.order.Order;
import entity.Product;
import exception.DAOException;
import storage.OrderItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Vitalii_Bandura on 5/15/2015.
 */
public class OrderItemDAOImpl implements OrderItemDAO {
    @Override
    public boolean addOrderItems(Connection connection, Order order) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("INSERT INTO order_item (order_id, product_id, quantity, price) VALUES(?,?,?,?)");
            Map<Product, Integer> products = order.getProducts();
            for (Product product : products.keySet()) {
                statement.setString(1, order.getId());
                statement.setInt(2, product.getId());
                statement.setInt(3, products.get(product));
                statement.setInt(4, product.getPrice());
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException e) {
            throw new DAOException("Cannot add order to DB", e);
        }
        return true;
    }
}
