package jstl_functions;

/**
 * Created by Vitalii_Bandura on 5/5/2015.
 */
public class Custom {
    public static boolean contains(String[] array, String value) {
        if (array != null && value != null) {
            for (String s : array) {
                if (s.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
