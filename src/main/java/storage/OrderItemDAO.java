package storage;

import entity.order.Order;

import java.sql.Connection;

/**
 * Created by Vitalii_Bandura on 5/15/2015.
 */
public interface OrderItemDAO {
    boolean addOrderItems(Connection connection, Order order);
}
