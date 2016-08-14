package service.implementation;

import entity.order.Order;
import service.OrderService;
import storage.OrderDAO;
import storage.OrderItemDAO;
import transaction.TransactionManager;
import transaction.TransactionOperation;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Виталий on 14.05.2015.
 */
public class OrderServiceImpl implements OrderService {
    private OrderDAO storage;
    private OrderItemDAO orderItemStorage;
    private TransactionManager transactionManager;

    public OrderServiceImpl(OrderDAO storage, OrderItemDAO orderItemStorage, TransactionManager transactionManager) {
        this.orderItemStorage = orderItemStorage;
        this.storage = storage;
        this.transactionManager = transactionManager;
    }

    public Boolean addOrder(final Order order) {
        return transactionManager.doTransaction(new TransactionOperation<Boolean>() {
            @Override
            public Boolean doTransaction(Connection connection) throws SQLException {
                return storage.addOrder(connection, order) && orderItemStorage.addOrderItems(connection, order);
            }
        });
    }
}
