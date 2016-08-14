package facet;

import entity.FilterBean;
import query_builder.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii_Bandura on 5/8/2015.
 */
public class FilterCreator {
    private FilterBean bean;

    public FilterCreator(FilterBean bean) {
        this.bean = bean;
    }

    public Query build() {
        List<Range> ranges = new ArrayList<Range>();
        String[] prices = bean.getPrice();
        if (prices != null) {
            for (String price : prices) {
                ranges.add(new Range(price));
            }
        }

        int offset = (bean.getPageNumber() - 1) * bean.getPerPage();
        if (bean.getPageNumber() > bean.getNumberOfPages()) {
            offset = 0;
        }
        Query query = new QueryBuilder()
                .select("product_id", "producers.producer", "model", "price", "gender", "details", "frame_size", "photo")
                .from("products")
                .join("producers", "products.producer_id=producers.producer_id")
                .where()
                .in("gender", bean.getGender())
                .and()
                .in("producers.producer", bean.getBrand())
                .and()
                .in("frame_size", bean.getFrameSize())
                .and()
                .between("price", ranges)
                .and()
                .like("model", bean.getModel())
                .orderBy(SortingOrder.define(bean.getSortBy()), SortingDirection.define(bean.getSortDirection()))
                .limit(offset, bean.getPerPage());
        return query;
    }


}
