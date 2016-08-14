package i18n;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import web.locale.provider.LocaleCookieProvider;
import web.locale.provider.LocaleSessionProvider;
import web.servlet.filter.LocaleFilter;
import web.wrapper.LocaleRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class LocaleFilterTest {
    private final static String STORAGE = "localeStorage";
    private final static String LANGUAGE = "language";

    private LocaleFilter localeFilter;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private FilterConfig filterConfig;

    @Mock
    private HttpSession session;

    @Mock
    private FilterChain chain;

    @Mock
    private ServletContext context;


    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        localeFilter = new LocaleFilter();
        when(context.getAttribute("supportedLanguages")).thenReturn(new ArrayList<Locale>() {{
            add(new Locale("en"));
            add(new Locale("ru"));
            add(new Locale("ua"));
        }});
        when(httpServletRequest.getSession()).thenReturn(session);
        when(context.getAttribute("defaultLanguage")).thenReturn(new Locale("ru"));
        when(context.getAttribute("localeStorageMaxAge")).thenReturn(100);
        when(filterConfig.getServletContext()).thenReturn(context);
        when(httpServletRequest.getServletContext()).thenReturn(context);
    }


    @Test
    public void testSessionDefaultLocale() throws Exception {
        when(httpServletRequest.getLocales()).thenReturn(Collections.enumeration(Collections.singletonList(new Locale("kr"))));
        when(context.getAttribute(STORAGE)).thenReturn(new LocaleSessionProvider());

        LocaleRequestWrapper wrapper = new LocaleRequestWrapper(httpServletRequest);
        PowerMockito.whenNew(LocaleRequestWrapper.class).withArguments(httpServletRequest).thenReturn(wrapper);

        localeFilter.init(filterConfig);
        localeFilter.doFilter(httpServletRequest, httpServletResponse, chain);

        assertEquals("ru", Collections.list(wrapper.getLocales()).get(0).getLanguage());
    }

    @Test
    public void testSessionLocaleFromAcceptLang() throws Exception {
        when(httpServletRequest.getLocales()).thenReturn(Collections.enumeration(Collections.singletonList(new Locale("en"))));
        when(context.getAttribute(STORAGE)).thenReturn(new LocaleSessionProvider());

        LocaleRequestWrapper wrapper = new LocaleRequestWrapper(httpServletRequest);
        PowerMockito.whenNew(LocaleRequestWrapper.class).withArguments(httpServletRequest).thenReturn(wrapper);

        localeFilter.init(filterConfig);
        localeFilter.doFilter(httpServletRequest, httpServletResponse, chain);

        assertEquals("en", Collections.list(wrapper.getLocales()).get(0).getLanguage());
    }

    @Test
    public void testSessionLocaleFromRequestParam() throws Exception {
        when(httpServletRequest.getParameter(LANGUAGE)).thenReturn("ua");
        when(session.getAttribute(LANGUAGE)).thenReturn(new Locale("ua"));
        when(httpServletRequest.getLocales()).thenReturn(Collections.enumeration(Collections.singletonList(new Locale("kr"))));
        when(context.getAttribute(STORAGE)).thenReturn(new LocaleSessionProvider());

        LocaleRequestWrapper wrapper = new LocaleRequestWrapper(httpServletRequest);
        PowerMockito.whenNew(LocaleRequestWrapper.class).withArguments(httpServletRequest).thenReturn(wrapper);

        localeFilter.init(filterConfig);
        localeFilter.doFilter(httpServletRequest, httpServletResponse, chain);

        assertEquals("ua", Collections.list(wrapper.getLocales()).get(0).getLanguage());
    }

    @Test
    public void testSessionLocaleFromSessionAttributes() throws Exception {
        when(session.getAttribute(LANGUAGE)).thenReturn(new Locale("ua"));
        when(httpServletRequest.getLocales()).thenReturn(Collections.enumeration(Collections.singletonList(new Locale("kr"))));
        when(context.getAttribute(STORAGE)).thenReturn(new LocaleSessionProvider());

        LocaleRequestWrapper wrapper = new LocaleRequestWrapper(httpServletRequest);
        PowerMockito.whenNew(LocaleRequestWrapper.class).withArguments(httpServletRequest).thenReturn(wrapper);

        localeFilter.init(filterConfig);
        localeFilter.doFilter(httpServletRequest, httpServletResponse, chain);

        assertEquals("ua", Collections.list(wrapper.getLocales()).get(0).getLanguage());
    }


    @Test
    public void testCookieDefaultLocale() throws Exception {
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie("e", "e")});
        when(httpServletRequest.getLocales()).thenReturn(Collections.enumeration(Collections.singletonList(new Locale("kr"))));
        when(context.getAttribute(STORAGE)).thenReturn(new LocaleCookieProvider());

        LocaleRequestWrapper wrapper = new LocaleRequestWrapper(httpServletRequest);
        PowerMockito.whenNew(LocaleRequestWrapper.class).withArguments(httpServletRequest).thenReturn(wrapper);

        localeFilter.doFilter(httpServletRequest, httpServletResponse, chain);
        localeFilter.init(filterConfig);

        assertEquals("ru", Collections.list(wrapper.getLocales()).get(0).getLanguage());
    }

    @Test
    public void testCookieLocaleFromAcceptLang() throws Exception {
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie("e", "e")});
        when(httpServletRequest.getLocales()).thenReturn(Collections.enumeration(Collections.singletonList(new Locale("en"))));
        when(context.getAttribute(STORAGE)).thenReturn(new LocaleCookieProvider());

        LocaleRequestWrapper wrapper = new LocaleRequestWrapper(httpServletRequest);
        PowerMockito.whenNew(LocaleRequestWrapper.class).withArguments(httpServletRequest).thenReturn(wrapper);

        localeFilter.init(filterConfig);
        localeFilter.doFilter(httpServletRequest, httpServletResponse, chain);

        assertEquals("en", Collections.list(wrapper.getLocales()).get(0).getLanguage());
    }

    @Test
    public void testCookieLocaleFromRequestParam() throws Exception {
        when(httpServletRequest.getParameter(LANGUAGE)).thenReturn("ua");
        when(session.getAttribute(LANGUAGE)).thenReturn(new Locale("ua"));
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie("e", "e")});
        when(httpServletRequest.getLocales()).thenReturn(Collections.enumeration(Collections.singletonList(new Locale("en"))));
        when(context.getAttribute(STORAGE)).thenReturn(new LocaleCookieProvider());

        LocaleRequestWrapper wrapper = new LocaleRequestWrapper(httpServletRequest);
        PowerMockito.whenNew(LocaleRequestWrapper.class).withArguments(httpServletRequest).thenReturn(wrapper);

        localeFilter.init(filterConfig);
        localeFilter.doFilter(httpServletRequest, httpServletResponse, chain);

        assertEquals("ua", Collections.list(wrapper.getLocales()).get(0).getLanguage());
    }

    @Test
    public void testCookieLocaleFromCookies() throws Exception {
        when(httpServletRequest.getCookies()).thenReturn(new Cookie[]{new Cookie(LANGUAGE, "ru")});
        when(httpServletRequest.getLocales()).thenReturn(Collections.enumeration(Collections.singletonList(new Locale("en"))));
        when(context.getAttribute(STORAGE)).thenReturn(new LocaleCookieProvider());

        LocaleRequestWrapper wrapper = new LocaleRequestWrapper(httpServletRequest);
        PowerMockito.whenNew(LocaleRequestWrapper.class).withArguments(httpServletRequest).thenReturn(wrapper);

        localeFilter.init(filterConfig);
        localeFilter.doFilter(httpServletRequest, httpServletResponse, chain);

        assertEquals("ru", Collections.list(wrapper.getLocales()).get(0).getLanguage());
    }

}
