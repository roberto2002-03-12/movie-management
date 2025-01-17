package com.robert.projects.MovieManagement.service.validator;

import com.robert.projects.MovieManagement.exception.throwable.InvalidPasswordException;
import org.springframework.util.StringUtils;

public class PasswordValidator {

    public static void validatePassword(String password, String passwordConfirmation) {

        if(!StringUtils.hasText(password) || !StringUtils.hasText(passwordConfirmation))
            throw new InvalidPasswordException(null, "Password and password confirmation are required");

        if(!password.equals(passwordConfirmation))
            throw new InvalidPasswordException(null, "Password and password confirmation must be equal");

        if(!containsNumber(password))
            throw new InvalidPasswordException(password, "Password must contain at least one number");

        if(!containsUpperCase(password))
            throw new InvalidPasswordException(password, "Password must contain at least one upper case letter");

        if(!containsLowerCase(password))
            throw new InvalidPasswordException(password, "Password must contain at least one lower case letter");
        
        if(!containsSpecialCharacter(password))
            throw new InvalidPasswordException(password, "Password must contain at least one special character");

    }

    private static boolean containsSpecialCharacter(String password) {
        return password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
    }

    private static boolean containsLowerCase(String password) {
        return password.matches(".*[a-z]+.*");
    }

    private static boolean containsUpperCase(String password) {
        return password.matches(".*[A-Z]+.*");
    }

    private static boolean containsNumber(String password) {
        return password.matches(".*\\d+.*");
    }

}
