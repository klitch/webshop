package query_builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Виталий on 09.05.2015.
 */
public class Query {
    private StringBuilder statement = new StringBuilder();
    private List<String> parameters = new ArrayList<String>();
    private String lastAddedOperator;

    public Query(Query query) {
        this.statement.append(query.toString());
        parameters = query.getParameters();
        lastAddedOperator = query.lastAddedOperator;
    }

    public Query(String statement) {
        this.statement.append(statement);
    }

    public Query() {
    }

    public StringBuilder append(String str) {
        return statement.append(str);
    }

    public StringBuilder append(int i) {
        return statement.append(i);
    }

    public StringBuilder append(double d) {
        return statement.append(d);
    }

    public int indexOf(String str) {
        return statement.indexOf(str);
    }

    public int lastIndexOf(String str) {
        return statement.lastIndexOf(str);
    }

    @Override
    public String toString() {
        return statement.toString();
    }

    public int length() {
        return statement.length();
    }

    public void addParameter(String param) {
        parameters.add(param);
    }

    public List<String> getParameters() {
        return new ArrayList<String>(parameters);
    }

    public void addConjunctionOperator(String operator) {
        lastAddedOperator = operator;
        statement.append(operator);
    }

    public void removeConjunctionOperator() {
        if (lastAddedOperator == null) {
            return;
        }
        statement.delete(statement.length() - lastAddedOperator.length(), statement.length());
    }

}
