package query_builder;

/**
 * Created by Виталий on 09.05.2015.
 */
public class Range {
    private String from;
    private String to;

    public Range(String from, String to) {
        this.from = from;
        this.to = to;
    }


    public Range(String rangeWithDash) {
        String[] range = new String[0];
        if (rangeWithDash.indexOf(">") >= 0) {
            from = rangeWithDash.substring(1);
        } else if(rangeWithDash.indexOf("<") >= 0){
            to = rangeWithDash.substring(1);
        }else {
            range = rangeWithDash.split("-");
            if(range.length > 0) {
                from = range[0];
            }
        }
        if (range.length > 1) {
            to = range[1];
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

}