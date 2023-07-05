package com.vitalii.userservicemanagment.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LatinLetterValidatorTest {
    private static final String LATIN_LETTER_PATTERN = "^[a-zA-Z]+$";

    @Test
    void isValid_ok() {
        String name = "Vitalii";
        Pattern pattern = Pattern.compile(LATIN_LETTER_PATTERN);
        Matcher matcher = pattern.matcher(name);
        assertTrue(matcher.matches());
    }
}