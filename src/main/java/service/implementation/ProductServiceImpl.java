package service.implementation;

import query_builder.Query;
import entity.FilterBean;
import entity.Product;
import service.ProductService;
import storage.ProductDAO;
import transaction.TransactionManager;
import transaction.TransactionOperation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Vitalii_Bandura on 5/6/2015.
 */
public class ProductServiceImpl implements ProductService {
    private ProductDAO storage;
    private TransactionManager transactionManager;

    public ProductServiceImpl(ProductDAO storage, TransactionManager transactionManager) {
        this.storage = storage;
        this.transactionManager = transactionManager;
    }

    public List<String> getBrands() {
        return transactionManager.doTransaction(new TransactionOperation<List<String>>() {
            @Override
            public List<String> doTransaction(Connection connection) throws SQLException {
                return storage.getBrands(connection);
            }
        });
    }

    public List<Product> getProducts(final Query query, final FilterBean filter) {
        return transactionManager.doTransaction(new TransactionOperation<List<Product>>() {
            @Override
            public List<Product> doTransaction(Connection connection) throws SQLException {
                return storage.getProducts(connection, query, filter);
            }
        });
    }

    public Product getProductById(final int id) {
        return transactionManager.doTransaction(new TransactionOperation<Product>() {
            @Override
            public Product doTransaction(Connection connection) throws SQLException {
                return storage.getProductById(connection, id);
            }
        });
    }

    public int getProductsCount(final Query query){
        return transactionManager.doTransaction(new TransactionOperation<Integer>() {
            @Override
            public Integer doTransaction(Connection connection) throws SQLException {
                return storage.getProductsCount(connection, query);
            }
        });
    }
}
