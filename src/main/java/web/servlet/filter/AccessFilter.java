package web.servlet.filter;

import entity.Role;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import xml.SecurityFileXMLParser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "/AccessFilter", urlPatterns = {"/*"})
public class AccessFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(AccessFilter.class);
    private static final String LOGIN_PAGE = "login.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SecurityFileXMLParser parser = new SecurityFileXMLParser();
        try {
            parser.parse(new File(AccessFilter.class.getClassLoader().getResource("security-configuration.xml").getPath()));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        List<Role> roleNeed = new ArrayList<>();


        if (constraintURI(httpRequest.getRequestURI(), roleNeed)) {
            User user = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
            if (user == null) {
                LOG.info("Must login");
                request.setAttribute("errorMessage", "You have to log in to get access to this page");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return;
            } else {
                LOG.info("Access denied");
                if (!roleNeed.contains(user.getRole())) {
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access forbidden");
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    private boolean constraintURI(String URI, List<Role> roleNeed) {
        boolean isConstraint = false;
        Role[] roles = Role.values();
        for (int i = 0; i < roles.length; i++) {
            List<String> patterns = roles[i].getPatterns();
            for (String pattern : patterns) {
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(URI);
                boolean match = m.find();
                if (match) {
                    roleNeed.add(roles[i]);
                    isConstraint = true;
                }
            }
        }
        return isConstraint;
    }

    @Override
    public void destroy() {

    }
}
