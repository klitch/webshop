package query_builder;

import java.util.List;

/**
 * Created by Виталий on 09.05.2015.
 */
public class ConditionClause {
    private Query query;

    public ConditionClause(Query query) {
        this.query = query;
    }

    public Conjunction like(String fieldName, String valueToCompare) {
        if (isExists(valueToCompare)) {
            query.append(fieldName + " LIKE " + "?" + " ");
            query.addParameter("%" + valueToCompare + "%");
        } else {
            query.removeConjunctionOperator();
        }
        return new Conjunction(query);
    }

    public Conjunction equals(String fieldName, String value) {
        if (isExists(value)) {
            query.append(fieldName + "= ? ");
            query.addParameter(value);
        } else {
            query.removeConjunctionOperator();
        }
        return new Conjunction(query);
    }

    public Conjunction in(String field, String[] values) {
        if (isExists(values)) {
            query.append(field + " IN(");
            for (int i = 0; i < values.length; i++) {
                query.append("?");
                query.addParameter(values[i]);
                if (i != values.length - 1) {
                    query.append(", ");
                }
            }
            query.append(") ");
        } else {
            query.removeConjunctionOperator();
        }
        return new Conjunction(query);
    }

    public Conjunction between(String field, List<Range> ranges) {
        if (ranges.size() > 0) {
            query.append("(");
            for (int i = 0; i < ranges.size(); i++) {
                String from = ranges.get(i).getFrom();
                String to = ranges.get(i).getTo();
                if (from == null) {
                    query.append(field).append(" < ").append("?");
                    query.addParameter(to);
                } else if (to == null) {
                    query.append(field).append(" > ").append("?");
                    query.addParameter(from);
                } else {
                    query.append(field + " BETWEEN ")
                            .append("? AND ? ");
                    query.addParameter(from);
                    query.addParameter(to);
                }
                if (i != ranges.size() - 1) {
                    query.append("OR ");
                }
            }
            query.append(")");
        } else {
            query.removeConjunctionOperator();
        }
        return new Conjunction(query);
    }

    private boolean isExists(String value) {
        return value != null && !value.isEmpty();
    }

    private boolean isExists(String[] values) {
        return values != null && values.length > 0;
    }

}
