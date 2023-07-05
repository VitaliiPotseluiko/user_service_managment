package com.vitalii.userservicemanagment.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatinLetterValidator implements ConstraintValidator<LatinLetter, String> {
    private static final String LATIN_LETTER_PATTERN = "^[a-zA-Z]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(LATIN_LETTER_PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
