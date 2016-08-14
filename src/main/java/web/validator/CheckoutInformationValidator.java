package web.validator;

import entity.order.CheckoutFormBean;

import java.util.HashMap;
import java.util.Map;

public class CheckoutInformationValidator {
    private Map<String, String> errorMap = new HashMap<String, String>();

    public Map<String, String> validate(CheckoutFormBean form) {
        clean();
        validateCreditCard(form.getCreditCard());
        validateAddress(form.getAddress());
        return errorMap;
    }

    private void validateCreditCard(String card) {
        String regexp = "[0-9]{16}";
        if(card == null){return;}
        if (card == null || card.isEmpty()) {
            errorMap.put("cardError", "Please fill this field");
        } else if (!card.matches(regexp)) {
            errorMap.put("cardError", "Card number incorrect");
        }
    }

    private void validateAddress(String address) {
        String regexp = "[\\s\\dA-Za-z.,-]{5,20}";
        if(address == null){return;}
        if (address.isEmpty()) {
            errorMap.put("addressError", "Please fill this field");
        } else if (!address.matches(regexp)) {
            errorMap.put("addressError", "Address incorrect");
        }
    }


    private void clean() {
        errorMap = new HashMap<String, String>();
    }

}
