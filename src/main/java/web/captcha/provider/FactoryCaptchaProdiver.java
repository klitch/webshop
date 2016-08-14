package web.captcha.provider;

public class FactoryCaptchaProdiver {
    public static AbstractCaptchaProvider getInstance(String providerType) {
        if (providerType.equals("SESSION")) {
            return new CaptchaSessionProvider();
        } else if (providerType.equals("COOKIE")) {
            return new CaptchaCookieProvider();
        } else if (providerType.equals("HIDDEN")) {
            return new CaptchaHiddenFieldProvider();
        }
        throw new IllegalArgumentException("Undefined CaptchaProvider");
    }
}
