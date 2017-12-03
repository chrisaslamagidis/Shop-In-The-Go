package com.sirialkillers.shoponthego;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Giannis Kapsalas on 11/1/2017.
 */

public class LoginValidation {


    private String email;
    private String password;

    public LoginValidation(String email, String password) {
        this.email = email;
        this.password = password;
    }


    //Email verification

    public boolean isEmailValid() {
        if (isEmailNotEmpty() && isEmailLengthEnough()
                && EmailContainsDot() && EmailContainsDuck()
                && EmailPatternIsMatched())
            return true;
        return false;
    }

    public boolean isEmailNotEmpty() {
        return !email.isEmpty();
    }

    public boolean isEmailLengthEnough() {
        return email.length() > 8;
    }

    public boolean EmailContainsDot() {
        return email.contains(".");
    }

    public boolean EmailContainsDuck() {
        return email.contains("@");
    }

    public boolean EmailPatternIsMatched() {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Password verification

    public boolean isPasswordValid() {
        return  isPasswordNotEmpty() && isPasswordLengthEnough();

    }
    public boolean isPasswordNotEmpty() {
        return !password.isEmpty();

    }

    public boolean isPasswordLengthEnough() {
        return password.length()>=6;
    }



}
