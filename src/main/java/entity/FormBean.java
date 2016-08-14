package entity;

public class FormBean {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String photoName;
    private boolean agreeForNewsletter;
    private String captcha;
    private String validCaptcha;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the newsletter
     */
    public boolean isAgreeForNewsletter() {
        return agreeForNewsletter;
    }

    /**
     * @param newsletter the newsletter to set
     */
    public void setAgreeForNewsletter(boolean newsletter) {
        this.agreeForNewsletter = newsletter;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getValidCaptcha() {
        return validCaptcha;
    }

    public void setValidCaptcha(String validCaptcha) {
        this.validCaptcha = validCaptcha;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FormBean [firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
                + ", email=" + email + ", newsLetter=" + agreeForNewsletter + "]";
    }

    public User getUser() {
        User user = new User();
        user.setAgreeForNewsletter(agreeForNewsletter);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setPhotoName(photoName);
        return user;
    }


    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
