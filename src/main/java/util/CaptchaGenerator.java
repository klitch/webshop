package util;

import entity.Captcha;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class CaptchaGenerator {
    public static Captcha generate() {
        String captchaValue = new BigInteger(130, new Random(System.currentTimeMillis())).toString(32)
                .substring(0, 8);
        return new Captcha(captchaValue, System.currentTimeMillis(), String
                .valueOf(UUID.randomUUID()));
    }
}
