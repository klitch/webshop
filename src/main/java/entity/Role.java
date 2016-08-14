package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Виталий on 24.05.2015.
 */
public enum Role {
    USER, ADMIN;

    private List<String> patterns = new ArrayList<>();

    public void addPattern(String pattern) {
        patterns.add(pattern);
    }

    public List<String> getPatterns() {
        return new ArrayList<>(patterns);
    }

}
