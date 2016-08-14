package web.servlet.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.OrderService;
import service.ProductService;
import service.UserService;
import service.implementation.OrderServiceImpl;
import service.implementation.ProductServiceImpl;
import service.implementation.UserServiceImpl;
import storage.implementation.OrderDAOImpl;
import storage.implementation.OrderItemDAOImpl;
import storage.implementation.ProductDAOImpl;
import storage.implementation.UserDAOImpl;
import transaction.TransactionManager;
import web.captcha.provider.AbstractCaptchaProvider;
import web.captcha.provider.AbstractCaptchaProviderInnerStorage;
import web.captcha.provider.FactoryCaptchaProdiver;
import web.locale.provider.AbstractLocaleProvider;
import web.locale.provider.FactoryLocaleProvider;
import web.thread.CleanerThread;
import web.validator.SignUpFormValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.*;

/**
 * Application Lifecycle Listener implementation class StartListener
 */
@WebListener
public class AppContextListener implements ServletContextListener {
    private ServletContext context;

    private static final Logger LOG = LoggerFactory.getLogger(AppContextListener.class);

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("[DEBUG]	AppContextListener: Start to initialize Log4j");

        context = servletContextEvent.getServletContext();
        AbstractCaptchaProvider captchaProvider = FactoryCaptchaProdiver.getInstance(context
                .getInitParameter("captchaStorage"));
        context.setAttribute("captchaStorage", captchaProvider);
        LOG.debug("CaptchaProvider has been initialized");

        AbstractLocaleProvider localeProvider = FactoryLocaleProvider.getInstance(context.getInitParameter("localeStorage"));
        context.setAttribute("localeStorage", localeProvider);
        LOG.debug("LocaleProvider has been initialized");

        context.setAttribute("defaultLanguage", new Locale(context.getInitParameter("defaultLanguage")));
        context.setAttribute("localeStorageMaxAge", context.getInitParameter("localeStorageMaxAge"));
        context.setAttribute("supportedLanguages", getSupportedLocales());

        SignUpFormValidator validator = new SignUpFormValidator();
        context.setAttribute("validator", validator);

        TransactionManager tm = new TransactionManager();
        UserService userService = new UserServiceImpl(new UserDAOImpl(), tm);
        ProductService productService = new ProductServiceImpl(new ProductDAOImpl(), tm);
        OrderService orderService = new OrderServiceImpl(new OrderDAOImpl(), new OrderItemDAOImpl(), tm);

        context.setAttribute("userService", userService);
        context.setAttribute("productService", productService);
        context.setAttribute("orderService", orderService);

        LOG.debug("Services has been initialized");

        if (captchaProvider instanceof AbstractCaptchaProviderInnerStorage) {
            int captchaAge = Integer.parseInt(context.getInitParameter("captchaAge"));
            CleanerThread cleaner = new CleanerThread(
                    (AbstractCaptchaProviderInnerStorage) captchaProvider, captchaAge);
            cleaner.setDaemon(true);
            cleaner.start();
            LOG.debug("Cleaner thread started.");
        }
    }

    private List<Locale> getSupportedLocales(){
        String localesString = context.getInitParameter("supportedLanguages");
        String[] localesTrim = localesString.split(",");
        for (int i = 0; i < localesTrim.length; i++) {
            localesTrim[i] = localesTrim[i].trim();
        }
        List<Locale> locales = new ArrayList<>();
        for (String s : localesTrim) {
            locales.add(new Locale(s));
        }
        return locales;
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
