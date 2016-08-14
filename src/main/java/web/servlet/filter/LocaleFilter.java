package web.servlet.filter;

import web.locale.provider.AbstractLocaleProvider;
import web.wrapper.LocaleRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "/LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    private AbstractLocaleProvider provider;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        provider = (AbstractLocaleProvider) filterConfig.getServletContext().getAttribute("localeStorage");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LocaleRequestWrapper wrapper = new LocaleRequestWrapper((HttpServletRequest) request);
        String lang = request.getParameter("language");
        if (lang != null) {
            Locale choose = new Locale(lang);
            provider.setLocale(wrapper, (HttpServletResponse) response, choose);
        }
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {
    }


}
