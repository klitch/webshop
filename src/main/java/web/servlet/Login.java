package web.servlet;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(Login.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String errorMessage;
        String forward = "login.jsp";
        if (email == null || pass == null || email.isEmpty() || pass.isEmpty()) {
            errorMessage = "Fill the fields";
            request.setAttribute("errorMessage", errorMessage);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Error: " + errorMessage);
            }
            request.getRequestDispatcher(forward).forward(request, response);
            return;
        }
        if (!userService.isUserExists(email) || !userService.checkPassword(email, pass)) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Error: " + errorMessage);
            }
            request.getRequestDispatcher(forward).forward(request, response);
        } else {
            User user = userService.getUser(email);
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60 * 60);
            session.setAttribute("user", user);
            if (LOG.isTraceEnabled()) {
                LOG.trace("Set the session attribute 'user' => " + user);
            }
            forward = request.getHeader("referer");
            if (!forward.endsWith("login") && !forward.endsWith("login.jsp")) {
                response.sendRedirect(request.getHeader("referer"));
                return;
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }
}
