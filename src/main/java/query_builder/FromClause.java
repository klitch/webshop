package query_builder;

/**
 * Created by Vitalii_Bandura on 5/8/2015.
 */
public class FromClause{
    private Query query;

    public FromClause(Query query) {
        this.query = query;
    }

    public WhereClause from(String... tableName) {
        query.append("FROM ");
        for (int i = 0; i < tableName.length; i++) {
            query.append(tableName[i]);
            if (i != tableName.length - 1) {
                query.append(", ");
            }
        }
        query.append(" ");
        return new WhereClause(query);
    }
}
