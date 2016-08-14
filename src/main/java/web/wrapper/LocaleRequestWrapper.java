package web.wrapper;

import web.locale.provider.AbstractLocaleProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class LocaleRequestWrapper extends HttpServletRequestWrapper {
    private AbstractLocaleProvider provider;
    private HttpServletRequest request;

    public LocaleRequestWrapper(HttpServletRequest request) {
        super(request);
        provider = (AbstractLocaleProvider) getServletContext().getAttribute("localeStorage");
        this.request =request;
    }

    @Override
    public Locale getLocale() {
        Locale locale = provider.getLocale((HttpServletRequest) getRequest());
        if (locale == null) {
            locale = Collections.list(getLocales()).get(0);
        }
        return locale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        List<Locale> preferred = Collections.list(super.getLocales());
        @SuppressWarnings("unchecked") List<Locale> supported = (List<Locale>) getServletContext().getAttribute("supportedLanguages");
        preferred.retainAll(supported);

        Locale locale = provider.getLocale((HttpServletRequest) getRequest());

        if (preferred.size() == 0 && locale == null) {
            preferred.add((Locale) getServletContext().getAttribute("defaultLanguage"));
        } else if (locale != null && preferred.contains(locale)) {
            preferred.remove(locale);
            preferred.add(0, locale);
        } else if(locale != null) {
            preferred.add(0, locale);
        }
        return Collections.enumeration(preferred);
    }
}
