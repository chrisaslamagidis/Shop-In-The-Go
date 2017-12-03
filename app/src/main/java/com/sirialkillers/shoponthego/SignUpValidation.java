package com.sirialkillers.shoponthego;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Giannis Kapsalas on 11/1/2017.
 */

public class SignUpValidation {

    private String username;
    private String email;
    private String password;
    private String verifyPassword;


    public SignUpValidation(String username, String email, String password, String verifyPassword) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.verifyPassword = verifyPassword;
    }

    //Username verification

    public boolean isUsernameValid() {
        return isUsernameNotEmpty() && UsernameContainsOnlyNumbersAndLetters()
                && isUsernameLengthEnough() && isUsernameLengthLessThanAmount();
    }

    public boolean isUsernameNotEmpty() {
        return !username.isEmpty();
    }

    public boolean UsernameContainsOnlyNumbersAndLetters() {
        return username.matches("[a-zA-Z0-9]*");
    }

    public boolean isUsernameLengthEnough() {
        return username.length() > 5;
    }

    public boolean isUsernameLengthLessThanAmount() {
        return username.length() <= 25;
    }

    //Email verification

    public boolean isEmailValid() {
        return isEmailNotEmpty() && isEmailLengthEnough()
                && emailContainsDot() && emailContainsDuck()
                && emailPatternIsMatched();
    }

    public boolean isEmailNotEmpty() {
        return !email.isEmpty();
    }

    public boolean isEmailLengthEnough() {
        return email.length() >= 8;
    }

    public boolean emailContainsDot() {
        return email.contains(".");
    }

    public boolean emailContainsDuck() {
        return email.contains("@");
    }

    public boolean emailPatternIsMatched() {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Password verification

    public boolean isPasswordValid() {
        return isPasswordNotEmpty() && isPasswordLengthEnough();
    }

    public boolean isPasswordNotEmpty() {
        return !password.isEmpty();
    }

    public boolean isPasswordLengthEnough() {
        return password.length() >= 6;
    }


    //Check if verifyPassword is valid

    public boolean isVerifyPasswordValid() {
        return arePasswordAndVerifyTheSame() && isVerifyNotEmpty();
    }

    public boolean arePasswordAndVerifyTheSame() {
        return password.equals(verifyPassword);
    }

    public boolean isVerifyNotEmpty() {
        return !verifyPassword.isEmpty();
    }


}
