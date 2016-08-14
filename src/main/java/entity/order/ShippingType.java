package entity.order;

/**
 * Created by Vitalii_Bandura on 5/15/2015.
 */
public enum ShippingType {
    SELF_PICKUP("Self-pickup"), TO_DOOR("To the door");
    String name;

    ShippingType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }



}
