package web.captcha.provider;

import entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaHiddenFieldProvider extends AbstractCaptchaProviderInnerStorage {
    @Override
    public void setCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        captchas.put(captcha.getCaptchaId(), captcha);
        request.setAttribute("captchaId", captcha.getCaptchaId());
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        return captchas.get(request.getParameter("captchaId"));
    }
}
