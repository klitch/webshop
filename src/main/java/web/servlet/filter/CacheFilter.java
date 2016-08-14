package web.servlet.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "/CacheFilter", urlPatterns = {"/*"})
public class CacheFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(CacheFilter.class);
    private static final String CACHE_CONTROL_HEADER = "Cache-Control";
    private static final String EXPIRES_DATE_HEADER = "Expires";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader(CACHE_CONTROL_HEADER, "no-cache, no-store, must-revalidate");
        httpServletResponse.setDateHeader(EXPIRES_DATE_HEADER, 0);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
