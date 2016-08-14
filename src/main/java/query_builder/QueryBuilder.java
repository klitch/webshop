package query_builder;

/**
 * Created by Vitalii_Bandura on 5/8/2015.
 */
public class QueryBuilder {
    private Query query = new Query();

    public FromClause select() {
        query.append("SELECT * ");
        return new FromClause(query);
    }

    public FromClause select(String... columnName) {
        query.append("SELECT ");
        for (int i = 0; i < columnName.length; i++) {
            query.append(columnName[i]);
            if (i != columnName.length - 1) {
                query.append(", ");
            }
        }
        query.append(" ");
        return new FromClause(query);
    }
}
