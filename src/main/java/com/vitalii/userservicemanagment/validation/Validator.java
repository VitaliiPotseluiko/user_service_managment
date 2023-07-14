package com.vitalii.userservicemanagment.validation;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {
    private static final String LATIN_LETTER_PATTERN = "^[a-zA-Z]+$";
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-zA-z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{3,16}$";

    public boolean isLatinLetter(String value) {
        Pattern pattern = Pattern.compile(LATIN_LETTER_PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public boolean isPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isValidSize(String str) {
        return str.length() > 0 && str.length() <= 16;
    }
}
