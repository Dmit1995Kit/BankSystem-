package com.yakovlev.BankSystem.domain.validator;

import com.sun.org.slf4j.internal.LoggerFactory;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.Map;

public class AbstractValidator {
    private final Logger logger = (Logger) LoggerFactory.getLogger(AbstractValidator.class);

    void checkAllFieldsOnEmpty(Object obj, Map<String, String> errors) {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.get(obj) == null || f.get(obj).toString() == "" || (Integer) f.get(obj) == 0) {
                    errors.put(f.getName(), "Необходимо заполнить поле");
                }
            } catch (IllegalAccessException | ClassCastException e) {
                // ignore
            }
        }

    }
}
