package com.sirialkillers.shoponthego;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Giannis Kapsalas on 11/1/2017.
 */
public class LoginValidationTest {
    @Test
    public void isEmailValidTrue() throws Exception {
        LoginValidation user = new LoginValidation("watafak@ak.com","password");
        assertTrue("isEmailValidTrue Passed",user.isEmailValid());
    }
    @Test
    public void isEmailValidFalse() throws Exception {
        LoginValidation user = new LoginValidation("watafak78@#$%%^Aak.com","password");
        assertFalse("isEmailValidFalse Passed",user.isEmailValid());
    }
    @Test
    public void isEmailNotEmptyTrue() throws Exception {
        LoginValidation user = new LoginValidation("watafak@ak.com","password");
        assertTrue("isEmailNotEmptyTrue Passed",user.isEmailNotEmpty());
    }
    @Test
    public void isEmailNotEmptyFalse() throws Exception {
        LoginValidation user = new LoginValidation("","password");
        assertFalse("isEmailNotEmptyFalse Passed",user.isEmailNotEmpty());
    }

    @Test
    public void isEmailLengthEnoughTrue() throws Exception {
        LoginValidation user = new LoginValidation("watafak@ak.com","password");
        assertTrue("isEmailLengthEnough is passed",user.isEmailLengthEnough());
    }
    @Test
    public void isEmailLengthEnoughFalse() throws Exception {
        LoginValidation user = new LoginValidation("@.","password");
        assertFalse("isEmailLengthEnough is passed",user.isEmailLengthEnough());
    }

    @Test
    public void emailContainsDotTrue() throws Exception {
        LoginValidation user = new LoginValidation("watafak@ak.com","password");
        assertTrue("emailContainsDotTrue is passed",user.EmailContainsDot());
    }
    @Test
    public void emailContainsDotFalse() throws Exception {
        LoginValidation user = new LoginValidation("watafak@akcom","password");
        assertFalse("emailContainsDotFalse is passed",user.EmailContainsDot());
    }
    @Test
    public void emailContainsDuckTrue() throws Exception {
        LoginValidation user = new LoginValidation("watafak@ak.com","password");
        assertTrue("emailContainsDuckTrue is passed",user.EmailContainsDuck());
    }
    @Test
    public void emailContainsDuckFalse() throws Exception {
        LoginValidation user = new LoginValidation("watafakak.11com","password");
        assertFalse("emailContainsDuckTrue is passed",user.EmailContainsDuck());
    }

    @Test
    public void emailPatternIsMatchedTrue() throws Exception {
        LoginValidation user = new LoginValidation("anywayk@yahoo.gra","password");
        assertTrue("emailPatternIsMatched is passed",user.EmailPatternIsMatched());
    }
    @Test
    public void emailPatternIsMatchedFalse() throws Exception {
        LoginValidation user = new LoginValidation("anywayyahoo.gra","password");
        assertFalse("emailPatternIsMatched is passed",user.EmailPatternIsMatched());
    }

    @Test
    public void isPasswordValidTrue() throws Exception {
        LoginValidation user = new LoginValidation("anywayyahoo.gra","password");
        assertTrue("isPasswordValidTrue passed",user.isPasswordValid());
    }

    @Test
    public void isPasswordValidFalse() throws Exception {
        LoginValidation user = new LoginValidation("anywayyahoo.gra","123");
        assertFalse("isPasswordValidFalse passed",user.isPasswordValid());
    }
    @Test
    public void isPasswordNotEmptyTrue() throws Exception {
        LoginValidation user = new LoginValidation("anywayyahoo.gra","123");
        assertTrue("isPasswordValidTrue passed",user.isPasswordNotEmpty());
    }
    @Test
    public void isPasswordNotEmptyFalse() throws Exception {
        LoginValidation user = new LoginValidation("anywayyahoo.gra","");
        assertFalse("isPasswordNotEmptyFalse passed",user.isPasswordNotEmpty());
    }

    @Test
    public void isPasswordLengthEnoughTrue() throws Exception {
        LoginValidation user = new LoginValidation("anywayyahoo.gra","123456");
        assertTrue("isPasswordLengthEnoughTrue is passed",user.isPasswordLengthEnough());
    }
    @Test
    public void isPasswordLengthEnoughFalse() throws Exception {
        LoginValidation user = new LoginValidation("anywayyahoo.gra","aaaa");
        assertFalse("isPasswordLengthEnoughFalse is passed",user.isPasswordLengthEnough());
    }

}