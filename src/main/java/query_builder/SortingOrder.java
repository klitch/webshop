package query_builder;

/**
 * Created by Виталий on 10.05.2015.
 */
public enum SortingOrder {
    PRODUCER("producer"), PRICE("price");

    String name;

    SortingOrder(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static SortingOrder define(String value) {
        if (value == null) {
            return null;
        }
        SortingOrder sort;
        try {
            sort = SortingOrder.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            sort = null;
        }
        return sort;
    }

}
