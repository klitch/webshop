package web.locale.provider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public abstract class AbstractLocaleProvider {

    public abstract void setLocale(HttpServletRequest request,HttpServletResponse response, Locale locale);
    public abstract Locale getLocale(HttpServletRequest request);

}
