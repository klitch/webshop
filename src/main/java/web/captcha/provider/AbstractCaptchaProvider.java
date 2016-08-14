package web.captcha.provider;

import entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCaptchaProvider {
    public abstract void setCaptcha(HttpServletRequest request, HttpServletResponse response,
                                    Captcha captcha);

    public abstract Captcha getCaptcha(HttpServletRequest request);

}
