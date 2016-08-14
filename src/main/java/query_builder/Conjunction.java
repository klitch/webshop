package query_builder;

/**
 * Created by Vitalii_Bandura on 5/8/2015.
 */
public class Conjunction extends EndingClause {
    private Query query;

    public Conjunction(Query query) {
        super(query);
        this.query = query;
    }

    public ConditionClause and() {
        if(!checkWhereAndAddIfNeeded()){
            query.addConjunctionOperator("AND ");
        }
        return new ConditionClause(query);
    }

    public ConditionClause or() {
        if(!checkWhereAndAddIfNeeded()){
            query.addConjunctionOperator("OR ");
        }
        return new ConditionClause(query);
    }

    private boolean checkWhereAndAddIfNeeded(){
        if(query.indexOf("WHERE") < 0){
            query.addConjunctionOperator("WHERE ");
            return true;
        }
        return false;
    }

}
