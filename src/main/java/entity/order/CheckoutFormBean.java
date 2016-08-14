package entity.order;

/**
 * Created by Виталий on 15.05.2015.
 */
public class CheckoutFormBean {
    private String address;

    public CheckoutFormBean(String address, String creditCard) {
        this.address = address;
        this.creditCard = creditCard;
    }

    private String creditCard;

    public String getAddress() {
        return address;
    }


    public String getCreditCard() {
        return creditCard;
    }

}
