package com.sirialkillers.shoponthego;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Giannis Kapsalas on 11/1/2017.
 */
public class SignUpValidationTest {
    @Test
    public void isUsernameValidTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("isUsernameValidTrue has passed",user.isUsernameValid());
    }
    @Test
    public void isUsernameValidFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123!@#$@","email@yahoo.com","hereweare123","hereweare123");
        assertFalse("isUsernameValidFalse has passed",user.isUsernameValid());
    }

    @Test
    public void isUsernameNotEmptyTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("isUsernameNotEmptyTrue has passed",user.isUsernameNotEmpty());
    }
    @Test
    public void isUsernameNotEmptyFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("","email@yahoo.com","hereweare123","hereweare123");
        assertFalse("isUsernameNotEmptyFalse has passed",user.isUsernameNotEmpty());
    }


    @Test
    public void usernameContainsOnlyNumbersAndLettersTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("isUsernameNotEmptyTrue has passed",user.UsernameContainsOnlyNumbersAndLetters());
    }
    @Test
    public void usernameContainsOnlyNumbersAndLettersFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa1!@#$23","email@yahoo.com","hereweare123","hereweare123");
        assertFalse("isUsernameNotEmptyFalse has passed",user.UsernameContainsOnlyNumbersAndLetters());
    }

    @Test
    public void isUsernameLengthEnoughTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("isUsernameNotEmptyTrue has passed",user.isUsernameLengthEnough());
    }

    @Test
    public void isUsernameLengthEnoughFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kap2","email@yahoo.com","hereweare123","hereweare123");
        assertFalse("isUsernameNotEmptyFalse has passed",user.isUsernameLengthEnough());
    }

    @Test
    public void isUsernameLengthLessThanAmountTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("isUsernameLengthLessThanAmountTrue has passed",user.isUsernameLengthLessThanAmount());
    }
    @Test
    public void isUsernameLengthLessThanAmountFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kkappa123kappa123kappa123kappa123kappa123kappa123kappa123kappa123appa123","email@yahoo.com","hereweare123","hereweare123");
        assertFalse("isUsernameLengthLessThanAmountFalse has passed",user.isUsernameLengthLessThanAmount());
    }

    @Test
    public void isEmailValidTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("isEmailValidTrue has passed",user.isEmailValid());
    }
    @Test
    public void isEmailValidFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email.com","hereweare123","hereweare123");
        assertFalse("isEmailValidFalse has passed",user.isEmailValid());
    }

    @Test
    public void isEmailNotEmptyTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("isEmailValidTrue has passed",user.isEmailNotEmpty());
    }
    @Test
    public void isEmailNotEmptyFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","","hereweare123","hereweare123");
        assertFalse("isEmailNotEmptyFalse has passed",user.isEmailNotEmpty());
    }

    @Test
    public void isEmailLengthEnoughTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","el@ya.co","hereweare123","hereweare123");
        assertTrue("isEmailLengthEnoughTrue has passed",user.isEmailLengthEnough());
    }
    @Test
    public void isEmailLengthEnoughFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","el@y.m","hereweare123","hereweare123");
        assertFalse("isEmailLengthEnoughFalse has passed",user.isEmailLengthEnough());
    }

    @Test
    public void emailContainsDotTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","emai@oo.com","hereweare123","hereweare123");
        assertTrue("emailContainsDotTrue has passed",user.emailContainsDot());
    }

    @Test
    public void emailContainsDotFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","emai@oocom","hereweare123","hereweare123");
        assertFalse("emailContainsDotFalse has passed",user.emailContainsDot());
    }

    @Test
    public void emailContainsDuckTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("emailContainsDuckTrue has passed",user.emailContainsDuck());
    }

    @Test
    public void emailContainsDuckFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","emailyahoo.com","hereweare123","hereweare123");
        assertFalse("emailContainsDuckFalse has passed",user.emailContainsDuck());
    }

    @Test
    public void emailPatternIsMatchedTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("emailPatternIsMatchedTrue has passed",user.emailPatternIsMatched());
    }
    @Test
    public void emailPatternIsMatchedFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","emailyahoo@.com","hereweare123","hereweare123");
        assertFalse("emailPatternIsMatchedFalse has passed",user.emailPatternIsMatched());
    }

    @Test
    public void isPasswordValidTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","here%^","here%^");
        assertTrue("isPasswordValidTrue has passed",user.isPasswordValid());
    }
    @Test
    public void isPasswordValidFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","!@199","!@199");
        assertFalse("isPasswordValidFalse has passed",user.isPasswordValid());
    }

    @Test
    public void isPasswordNotEmptyTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("isPasswordNotEmptyTrue has passed",user.isPasswordNotEmpty());
    }
    @Test
    public void isPasswordNotEmptyFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","","hereweare123");
        assertFalse("isPasswordNotEmptyFalse has passed",user.isPasswordNotEmpty());
    }

    @Test
    public void isPasswordLengthEnoughTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","123123","hereweare123");
        assertTrue("isPasswordLengthEnoughTrue has passed",user.isPasswordLengthEnough());
    }
    @Test
    public void isPasswordLengthEnoughFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","12345","hereweare123");
        assertFalse("isPasswordLengthEnoughFalse has passed",user.isPasswordLengthEnough());
    }

    @Test
    public void isVerifyPasswordValidTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("isVerifyPasswordValidTrue has passed",user.isVerifyPasswordValid());
    }
    @Test
    public void isVerifyPasswordValidFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare1231231232","hereweare1231231231");
        assertFalse("isVerifyPasswordValidFalse has passed",user.isVerifyPasswordValid());
    }

    @Test
    public void arePasswordAndVerifyTheSameTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","youtouchmytralala","youtouchmytralala");
        assertTrue("arePasswordAndVerifyTheSameTrue has passed",user.arePasswordAndVerifyTheSame());
    }

    @Test
    public void arePasswordAndVerifyTheSameFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","youtsuchmytralala","youtouchmytralala");
        assertFalse("arePasswordAndVerifyTheSameFalse has passed",user.arePasswordAndVerifyTheSame());
    }

    @Test
    public void isVerifyNotEmptyTrue() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","hereweare123");
        assertTrue("isVerifyNotEmptyTrue has passed",user.isVerifyNotEmpty());
    }

    @Test
    public void isVerifyNotEmptyFalse() throws Exception {
        SignUpValidation user = new SignUpValidation("kappa123","email@yahoo.com","hereweare123","");
        assertFalse("isVerifyNotEmptyFalse has passed",user.isVerifyNotEmpty());
    }

}