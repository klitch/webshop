package web.servlet;


import entity.Captcha;
import entity.FormBean;
import web.multiform.PhotoUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;
import util.CaptchaGenerator;
import web.validator.SignUpFormValidator;
import web.captcha.provider.AbstractCaptchaProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class Register
 */
@WebServlet(name = "register", urlPatterns = {"/register"})
@MultipartConfig
public class Register extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(Register.class);
    private AbstractCaptchaProvider captchaProvider;
    private UserService userService;
    private SignUpFormValidator validator;

    @Override
    public void init() throws ServletException {
        captchaProvider = (AbstractCaptchaProvider)getServletContext().getAttribute("captchaStorage");
        userService = (UserService)getServletContext().getAttribute("userService");
        validator = (SignUpFormValidator)getServletContext().getAttribute("validator");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        captchaProvider.setCaptcha(request, response, CaptchaGenerator.generate());
        String forward = "register.jsp";
        request.getRequestDispatcher(forward).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Sign up servlet starts");
        }
        request.setCharacterEncoding("UTF-8");
        FormBean newUser = fillUserBean(request);
        Captcha captcha = captchaProvider.getCaptcha(request);

        String cp = null;
        if (captcha != null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Valid captcha: " + captcha.getCaptchaToken());
            }
            cp = captcha.getCaptchaToken();
        }
        newUser.setValidCaptcha(cp);

        String forward = "index.jsp";
        Map<String, String> errorMap;
        if (userService.isUserExists(newUser.getEmail())) {
            errorMap = new HashMap<String, String>();
            errorMap.put("emailMessage", "User with same email already exists");
        } else {
            errorMap = validator.validate(newUser);
        }
        if (errorMap.size() != 0) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("User form is invalid");
            }
            setFilledFields(request, newUser);
            for (String key : errorMap.keySet()) {
                request.setAttribute(key, errorMap.get(key));
            }
            doGet(request, response);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("User form is valid");
            }
            Part photo = request.getPart("file");
            String photoName = String.valueOf(newUser.getEmail().hashCode() + System.currentTimeMillis());
            PhotoUploader uploader = new PhotoUploader(photo, photoName);
            newUser.setPhotoName(uploader.upload());
            userService.addUser(newUser.getUser());
            response.sendRedirect(forward);
        }
        if (LOG.isTraceEnabled()) {
            LOG.trace("Controller finished it`s work, now go to forward address => " + forward);
        }
    }

    private void setFilledFields(HttpServletRequest request, FormBean newUser) {
        request.setAttribute("first_name", newUser.getFirstName());
        request.setAttribute("last_name", newUser.getLastName());
        request.setAttribute("email", newUser.getEmail());
        if (newUser.isAgreeForNewsletter()) {
            request.setAttribute("isAgreeForNewsletter", "checked");
        }
    }

    private FormBean fillUserBean(HttpServletRequest request) {
        FormBean newUser = new FormBean();
        newUser.setLastName(request.getParameter("last_name"));
        newUser.setFirstName(request.getParameter("first_name"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setAgreeForNewsletter(request.getParameter("newsCheckBox") != null);
        newUser.setCaptcha(request.getParameter("captchaValue"));
        return newUser;
    }

}
