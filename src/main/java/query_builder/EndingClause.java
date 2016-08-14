package query_builder;

/**
 * Created by Vitalii_Bandura on 5/8/2015.
 */
public class EndingClause {
    private Query query;

    public EndingClause(Query query) {
        this.query = query;
    }

    public EndingClause orderBy(SortingOrder fieldName, SortingDirection direction) {
        if (fieldName != null) {
            if (query.indexOf("ORDER BY") > 0) {
                query.append(", ");
            } else {
                query.append("ORDER BY ");
            }
            query.append(fieldName.toString()).append(" ");
            if (direction != null) {
                query.append(direction + " ");
            }
        }
        return EndingClause.this;
    }

    public Query limit(int offset, int rows) {
        query.append("LIMIT ").append(offset).append(", ").append(rows);
        return query;
    }

    public Query build() {
        return new Query(query);
    }
}
