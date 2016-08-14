package web.validator;

import entity.FormBean;

import java.util.HashMap;
import java.util.Map;

public class SignUpFormValidator {
    private Map<String, String> errorMap = new HashMap<String, String>();

    public Map<String, String> validate(FormBean form) {
        clean();
        if (!form.getCaptcha().equals(form.getValidCaptcha())) {
            errorMap.put("errorMessage", "Captcha incorrect");
            return errorMap;
        }
        validateName(form.getFirstName(), "firstNameMessage");
        validateName(form.getLastName(), "lastNameMessage");
        validateEmail(form.getEmail());
        validatePass(form.getPassword());
        return errorMap;
    }

    private void validateName(String name, String messageBox) {
        String regexp = "[a-zA-Z\\u0410-\\u044f]+$";
        if (name.isEmpty()) {
            errorMap.put(messageBox, "Please fill this field");
        } else if (!name.matches(regexp)) {
            errorMap.put(messageBox, "Name incorrect");
        }
    }

    private void validateEmail(String email) {
        String regexp = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)+$";
        String errorBox = "emailMessage";
        if (email.isEmpty()) {
            errorMap.put(errorBox, "Please fill this field");
        } else if (!email.matches(regexp)) {
            errorMap.put(errorBox, "Email incorrect");
        }
    }

    private void validatePass(String pass) {
        String regexp = "^(?=.*\\d)(?=.*[a-zA-Z\\u0410-\\u044f])\\w{6,100}+$";
        String errorBox = "passMessage";
        if (pass.isEmpty()) {
            errorMap.put(errorBox, "Please fill this field");
        } else if (!pass.matches(regexp)) {
            errorMap.put(errorBox, "Pass incorrect");
        }
    }

    private void clean() {
        errorMap = new HashMap<String, String>();
    }

}
