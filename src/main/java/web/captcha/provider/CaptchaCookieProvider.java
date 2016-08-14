package web.captcha.provider;

import entity.Captcha;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaCookieProvider extends AbstractCaptchaProviderInnerStorage {

    @Override
    public void setCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        captchas.put(captcha.getCaptchaId(), captcha);
        Cookie cook = new Cookie("captchaId", captcha.getCaptchaId());
        response.addCookie(cook);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        String captchaId = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("captchaId")) {
                captchaId = cookie.getValue();
            }
        }
        return captchas.get(captchaId);
    }

}
