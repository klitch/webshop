package entity.order;

/**
 * Created by Vitalii_Bandura on 5/15/2015.
 */
public enum PaymentType {
    CASH("Cash"), CARD("Card");
    String name;

    PaymentType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
