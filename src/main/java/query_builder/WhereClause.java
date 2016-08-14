package query_builder;

/**
 * Created by Vitalii_Bandura on 5/8/2015.
 */
public class WhereClause extends JoinClause{
    private Query query;

    public WhereClause(Query query) {
        super(query);
        this.query = query;
    }

    public ConditionClause where() {
        query.addConjunctionOperator("WHERE ");
        return new ConditionClause(query);
    }

}
