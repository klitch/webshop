package web.locale.provider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Vitalii_Bandura on 5/19/2015.
 */
public class LocaleCookieProvider extends AbstractLocaleProvider {
    private Locale locale;
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        Cookie cookie = new Cookie("language", locale.toString());
        this.locale = locale;
        cookie.setPath("/");
        cookie.setMaxAge(Integer.parseInt(request.getServletContext().getAttribute("localeStorageMaxAge").toString()));
        response.addCookie(cookie);
    }

    @Override
    public Locale getLocale(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("language")) {
                    if (locale != null && !cookies[i].getValue().equals(locale.getLanguage())) {
                        String loc = locale.getLanguage();
                        return new Locale(loc);
                    }
                    return new Locale(cookies[i].getValue());
                }
            }
        }
        return locale;
    }
}
