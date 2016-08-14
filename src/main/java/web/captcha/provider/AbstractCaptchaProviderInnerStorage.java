package web.captcha.provider;

import entity.Captcha;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractCaptchaProviderInnerStorage extends AbstractCaptchaProvider {
    protected Map<String, Captcha> captchas = new ConcurrentHashMap<String, Captcha>();

    public Map<String, Captcha> getCaptchas() {
        return captchas;
    }
}
