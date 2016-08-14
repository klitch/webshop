package web.thread;

import entity.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.captcha.provider.AbstractCaptchaProviderInnerStorage;

import java.util.Map;

public class  CleanerThread extends Thread {
    private static final Logger LOG = LoggerFactory.getLogger(CleanerThread.class);
    private AbstractCaptchaProviderInnerStorage provider;
    private long maxCaptchaAge;

    public CleanerThread(AbstractCaptchaProviderInnerStorage provider, long maxCaptchaAge) {
        this.provider = provider;
        this.maxCaptchaAge = maxCaptchaAge;
    }

    @Override
    public void run() {
        Map<String, Captcha> captchas = provider.getCaptchas();
        while (true) {
            if (captchas.size() != 0) {
                for (String id : captchas.keySet()) {
                    if (System.currentTimeMillis() - captchas.get(id).getCreationTime() > maxCaptchaAge) {
                        captchas.remove(id);
                    }
                }
                LOG.debug("Captchas with expired age deleted");
            }
            try {
                sleep(maxCaptchaAge);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
