package storage.implementation;

import entity.FilterBean;
import entity.Gender;
import entity.Product;
import entity.Size;
import exception.DAOException;
import query_builder.Query;
import query_builder.QueryBuilder;
import storage.ProductDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii_Bandura on 5/6/2015.
 */
public class ProductDAOImpl implements ProductDAO {
    public static final String SELECT_ALL_BRANDS = "SELECT producer FROM producers";

    public List<String> getBrands(Connection connection) {
        List<String> brands = new ArrayList<String>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_BRANDS);
            while (resultSet.next()) {
                brands.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get producers from DB", e);
        }
        return brands;
    }

    public List<Product> getProducts(Connection connection, Query query, FilterBean filter) {
        List<Product> products = new ArrayList<Product>();
        try {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement = setValuesOfPreparedStatement(query, statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get products from DB", e);
        }
        return products;
    }

    public Product getProductById(Connection connection, int id) {
        Product product = null;
        Query query = new QueryBuilder()
                .select("product_id", "producers.producer", "model", "price", "gender", "details", "frame_size", "photo")
                .from("products")
                .join("producers", "products.producer_id=producers.producer_id")
                .where()
                .equals("product_id", String.valueOf(id))
                .build();
        try {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            statement = setValuesOfPreparedStatement(query, statement);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            product = getProductFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Cannot get product by id from DB", e);
        }
        return product;
    }

    private PreparedStatement setValuesOfPreparedStatement(Query query, PreparedStatement statement) throws SQLException {
        List<String> params = query.getParameters();
        for (int i = 1; i <= params.size(); i++) {
            statement.setString(i, params.get(i - 1));
        }
        return statement;
    }

    public Integer getProductsCount(Connection connection, Query query) {
        Integer count;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query.toString());
            statement = setValuesOfPreparedStatement(query, statement);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException("Cannot get count of products from DB", e);
        }
        return count;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(1));
        product.setProducer(resultSet.getString(2));
        product.setModel(resultSet.getString(3));
        product.setPrice(resultSet.getInt(4));
        product.setGender(Enum.valueOf(Gender.class, resultSet.getString(5).toUpperCase()));
        product.setDetails(resultSet.getString(6));
        product.setSize(Enum.valueOf(Size.class, resultSet.getString(7).toUpperCase()));
        product.setPhoto(resultSet.getString(8));
        return product;
    }
}
