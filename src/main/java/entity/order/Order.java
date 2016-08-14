package entity.order;

import entity.Product;

import java.util.Date;
import java.util.Map;

/**
 * Created by Виталий on 14.05.2015.
 */
public class Order {
    private String id;
    private String userId;
    private String address;
    private String creditCard;
    private String paymentType;
    private String shippingType;
    private OrderStatus status;
    private Date dateOfCreation;
    private Map<Product, Integer> products;

    public Order(String id, String userId, String address, String creditCard, String paymentType, String shippingType, OrderStatus status, Date dateOfCreation, Map<Product, Integer> products) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.creditCard = creditCard;
        this.paymentType = paymentType;
        this.shippingType = shippingType;
        this.status = status;
        this.dateOfCreation = dateOfCreation;
        this.products = products;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getShippingType() {
        return shippingType;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Date getDateOfCreation() {
        return new Date(dateOfCreation.getTime());
    }

    public String getAddress() {
        return address;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
