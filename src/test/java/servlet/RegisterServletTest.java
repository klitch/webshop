package servlet;

import entity.Captcha;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import service.UserService;
import service.implementation.UserServiceImpl;
import storage.implementation.UserDAOImpl;
import transaction.TransactionManager;
import web.validator.SignUpFormValidator;
import web.captcha.provider.CaptchaSessionProvider;
import web.servlet.Register;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletContext context;

    @Mock
    private ServletConfig config;

    private UserService userService;
    private Register registerServlet;

    @Before
    public void init() throws ServletException, IOException {
        RequestDispatcher dispatcher = Mockito.spy(RequestDispatcher.class);
        CaptchaSessionProvider provider = mock(CaptchaSessionProvider.class);
        SignUpFormValidator validator = new SignUpFormValidator();
        TransactionManager tm = new TransactionManager();

        registerServlet = new Register();
        userService = new UserServiceImpl(new UserDAOImpl(), tm);

        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("captchaStorage")).thenReturn(provider);
        when(context.getAttribute("userService")).thenReturn(userService);
        when(context.getAttribute("validator")).thenReturn(validator);
        when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(dispatcher);
        Mockito.doNothing().when(dispatcher).forward(request, response);
        when(request.getParameter("last_name")).thenReturn("LastName");
        when(request.getParameter("first_name")).thenReturn("FirstName");
        when(request.getParameter("email")).thenReturn("Email@mail.ru");
        when(request.getParameter("password")).thenReturn("alalalaLala19");
        when(request.getParameter("newsCheckBox")).thenReturn("asd");
        when(request.getParameter("captchaValue")).thenReturn("alalalal");
        when(provider.getCaptcha(request)).thenReturn(
                new Captcha("alalalal", System.currentTimeMillis(), "123"));
        registerServlet.init(config);
    }

    @Test
    public void testRegisterWhenUserValidThenAddHim() throws ServletException, IOException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        when(request.getParameter("email")).thenReturn("Email@mail.ru");
        when(request.getParameter("password")).thenReturn("alalalaLala19");
        registerServlet.doPost(request, response);
        User user = userService.getUser("Email@mail.ru");
        assertTrue(user != null);
    }


    @Test
    public void testRegisterWhenUserInvalidThenRegistrationFails() throws ServletException, IOException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        when(request.getParameter("email")).thenReturn("Email4@mail.ru");
        when(request.getParameter("password")).thenReturn("alal");
        registerServlet.doPost(request, response);
        User user = userService.getUser("Email4@mail.ru");
        assertTrue(user == null);
    }

}
