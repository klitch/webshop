package entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Виталий on 11.05.2015.
 */
public class Cart {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public Integer getQuantity(Object key) {
        return products.get(key);
    }

    public void add(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    public void remove(Product product) {
        products.remove(product);
    }

    public void decrementQuantity(Product product) {
        if (products.containsKey(product)) {
            int quantity = products.get(product);
            if (quantity > 1) {
                products.replace(product, products.get(product) - 1);
            }
        }
    }

    public void changeQuantity(Product product, int newQuantity) {
        if (products.containsKey(product)) {
            if (newQuantity > 0)
                products.replace(product, newQuantity);
        }
    }

    public int size() {
        int size = 0;
        for (Product product : products.keySet()) {
            size += products.get(product);
        }
        return size;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }

    public Set<Product> productSet() {
        return products.keySet();
    }

    public Map<Product, Integer> getAllProducts(){
        return new HashMap<Product, Integer>(products);
    }

    public int getTotal() {
        int sum = 0;
        for (Product product : products.keySet()) {
            sum += product.getPrice() * products.get(product);
        }
        return sum;
    }

    public void clean(){
        products.clear();
    }
}
