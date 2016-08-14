package web.captcha.provider;

import entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaSessionProvider extends AbstractCaptchaProvider {

    @Override
    public void setCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        request.getSession().setAttribute("captcha", captcha);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        return (Captcha) request.getSession().getAttribute("captcha");
    }

}
