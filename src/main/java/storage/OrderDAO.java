package storage;

import entity.order.Order;

import java.sql.Connection;

/**
 * Created by ������� on 14.05.2015.
 */
public interface OrderDAO {
    boolean addOrder(Connection connection, Order order);
}
