package entity;

import java.io.Serializable;

/**
 * Created by Виталий on 04.05.2015.
 */
public class Product implements Serializable{
    private int id;
    private String producer;
    private String model;
    private int price;
    private Gender gender;
    private String details;
    private Size size;
    private String photo;

    public Product() {
    }

    public Product(int id, String producer, String model, int price, Gender gender, String details, Size size, String photo) {
        this.id = id;
        this.producer = producer;
        this.model = model;
        this.price = price;
        this.gender = gender;
        this.details = details;
        this.size = size;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
