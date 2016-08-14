package service;

import query_builder.Query;
import entity.FilterBean;
import entity.Product;

import java.util.List;

/**
 * Created by Vitalii_Bandura on 5/6/2015.
 */
public interface ProductService {
    List<String> getBrands();
    List<Product> getProducts(Query query, FilterBean filter);
    Product getProductById(int id);
    int getProductsCount(Query query);
}
