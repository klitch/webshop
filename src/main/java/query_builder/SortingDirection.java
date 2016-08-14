package query_builder;

/**
 * Created by Виталий on 10.05.2015.
 */
public enum SortingDirection {
    ASC, DESC;

    public static SortingDirection define(String value) {
        if (value == null) {
            return null;
        }
        SortingDirection direction;
        try {
            direction = SortingDirection.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            direction = null;
        }
        return direction;
    }

}
