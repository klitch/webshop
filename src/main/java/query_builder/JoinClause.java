package query_builder;

/**
 * Created by Виталий on 09.05.2015.
 */
public class JoinClause extends EndingClause{
    private Query query;

    public JoinClause(Query query) {
        super(query);
        this.query = query;
    }

    public WhereClause join(String tableName, String on) {
        query.append("JOIN ").append(tableName).append(" ON ").append(on).append(" ");
        return new WhereClause(query);
    }
}
