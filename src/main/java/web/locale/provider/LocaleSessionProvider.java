package web.locale.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Vitalii_Bandura on 5/19/2015.
 */
public class LocaleSessionProvider extends AbstractLocaleProvider {
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        HttpSession session = request.getSession();
        session.setAttribute("language", locale);
        getLocale(request);
    }

    @Override
    public Locale getLocale(HttpServletRequest request) {
        Locale locale = (Locale) request.getSession().getAttribute("language");
        return locale;
    }
}
