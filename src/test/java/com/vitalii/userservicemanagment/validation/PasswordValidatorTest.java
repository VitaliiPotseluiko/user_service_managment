package com.vitalii.userservicemanagment.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PasswordValidatorTest {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-zA-z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{3,16}$";
    @Test
    void isValid_ok() {
        String name = "1704Vetal@";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(name);
        assertTrue(matcher.matches());
    }
}