package web.servlet;

import query_builder.Query;
import query_builder.QueryBuilder;
import query_builder.Range;
import entity.FilterBean;
import entity.Product;
import facet.FilterCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "catalog", urlPatterns = {"/catalog"})
public class Catalog extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(Catalog.class);
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FilterBean filterBean = null;
        try {
            filterBean = fillFilterBean(request);

            Query countQuery = createQueryForCount(filterBean);
            int productsCount = productService.getProductsCount(countQuery);
            int pageCount = (int)Math.ceil((double) productsCount / filterBean.getPerPage());
            filterBean.setNumberOfPages(pageCount);

            Query query = new FilterCreator(filterBean).build();
            List<Product> products = productService.getProducts(query, filterBean);

            request.setAttribute("pagesCount", pageCount);
            request.setAttribute("products", products);
        } catch (NumberFormatException e) {
            LOG.error("Error in request");
            request.setAttribute("products", null);
        } finally {
            request.setAttribute("brands", productService.getBrands());
            request.setAttribute("filter", filterBean);
            request.getRequestDispatcher("catalog.jsp").forward(request, response);
        }
    }

    private Query createQueryForCount(FilterBean bean) {
        List<Range> ranges = new ArrayList<Range>();
        String[] prices = bean.getPrice();
        if (prices != null) {
            for (String price : prices) {
                ranges.add(new Range(price));
            }
        }
        Query query = new QueryBuilder()
                .select("COUNT(*)")
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
                .build();
        return query;
    }

    private FilterBean fillFilterBean(HttpServletRequest request) {
        request.setAttribute("brands", productService.getBrands());
        FilterBean filterBean = FilterBean.getBuilder()
                .gender(request.getParameterValues("gender"))
                .price(request.getParameterValues("price"))
                .brand(request.getParameterValues("brand"))
                .frameSize(request.getParameterValues("size"))
                .sortDirection(request.getParameter("sortDirection"))
                .perPage(request.getParameter("perPage"))
                .pageNumber(request.getParameter("page"))
                .model(request.getParameter("model"))
                .sortBy(request.getParameter("sort"))
                .build();
        return filterBean;
    }
}
