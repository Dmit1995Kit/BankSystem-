package com.yakovlev.BankSystem.domain.validator;

import com.yakovlev.BankSystem.domain.service.UserService;
import com.yakovlev.BankSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserValidator extends AbstractValidator {
    @Autowired
    UserService userService;

    private static String fieldPass = "password";
    private static String fieldConfirmPass = "confirmPassword";

    public Map<String, String> validate(User user) {

        Map<String, String> errors = new HashMap<>();

        this.checkAllFieldsOnEmpty(user, errors);

        errors.remove("passport");

        // TODO validate all field

        if (!errors.containsKey(fieldPass) && !errors.containsKey(fieldConfirmPass)) {
            String password = user.getPassword();
            String confirmPassword = user.getConfirmPassword();

            if (password.length() < 6) {
                errors.put(fieldPass, "Минимальная длина 6 символов");
            }
            else if (password.length() > 50) {
                errors.put(fieldPass, "Максимальная длина 50 символов");
            }
            else if (!password.equals(confirmPassword)) {
                errors.put(fieldConfirmPass, "Пароли не совпадают");
            }
        }

        return errors;

    }
}
