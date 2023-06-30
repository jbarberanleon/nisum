package com.example.testnisum.helpers;

import java.util.regex.Pattern;
public class HelperValidator {

    public static boolean isValidPattern(String email, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).matches();
    }
}
