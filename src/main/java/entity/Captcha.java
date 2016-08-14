package entity;

public class Captcha {
    private String captchaToken;
    private String captchaId;
    private long creationTime;

    public Captcha(String captchaToken, long creationTime, String captchaId) {
        super();
        this.captchaToken = captchaToken;
        this.creationTime = creationTime;
        this.captchaId = captchaId;
    }

    /**
     * @return the captchaToken
     */
    public String getCaptchaToken() {
        return captchaToken;
    }

    /**
     * @return the creationTime
     */
    public long getCreationTime() {
        return creationTime;
    }

    public String getCaptchaId() {
        return captchaId;
    }

}
