package storage;

import query_builder.Query;
import entity.FilterBean;
import entity.Product;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Vitalii_Bandura on 5/6/2015.
 */
public interface ProductDAO {
    List<String> getBrands(Connection connection);
    List<Product> getProducts(Connection connection, Query query, FilterBean filter);
    Product getProductById(Connection connection, int id);
    Integer getProductsCount(Connection connection, Query query);


}
