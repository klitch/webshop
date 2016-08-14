package web.servlet.filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "/UserFilter", urlPatterns = {"/checkoutDetails.jsp", "/confirmation.jsp", "/orderSuccess.jsp"})
public class UserFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = (User) ((HttpServletRequest)request).getSession().getAttribute("user");
        if(user == null){
            request.setAttribute("errorMessage", "Log in before making order");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
