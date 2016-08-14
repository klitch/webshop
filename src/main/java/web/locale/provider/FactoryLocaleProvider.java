package web.locale.provider;

public class FactoryLocaleProvider {
    public static AbstractLocaleProvider getInstance(String providerType) {
        if (providerType.equals("SESSION")) {
            return new LocaleSessionProvider();
        } else if (providerType.equals("COOKIE")) {
            return new LocaleCookieProvider();
        }
        throw new IllegalArgumentException("Undefined LocaleProvider");
    }
}
