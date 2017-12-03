package com.sirialkillers.shoponthego;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Giannis on 3/11/2017.
 */
@RunWith(AndroidJUnit4.class)
public class SignUpActivityTest {
    @Rule
    public ActivityTestRule<SignUpActivity> mSignUpActivityTestRule = new ActivityTestRule<>(SignUpActivity.class);

    @Test
    public void clickRegisterAndLogin_CheckIfItDoesNothing() throws Exception{
        onView(withId(R.id.registerAndLoginButton)).perform(click());
        onView(withId(R.id.registerAndLoginButton)).check(matches(isDisplayed()));
    }

    @Test
    public void clickAlreadyHaveAnAccount_CheckIfLoginActivityIsDisplayed() throws Exception{
        onView(withId(R.id.goBackToLoginTextView)).perform(click());
        onView(withId(R.id.signUpTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void clickAlreadyHaveAnAccountAndThenClickOnSignUp_CheckIfSignUpActivityIsDisplayed() throws Exception{
        onView(withId(R.id.goBackToLoginTextView)).perform(click());
        onView(withId(R.id.signUpTextView)).perform(click());
        onView(withId(R.id.goBackToLoginTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void putSomeCredentialsAndLogin_CheckIfMapShows() throws Exception{
        onView(withId(R.id.usernameEditText)).perform(typeText("Tralalo123456778WOWHELLO"),closeSoftKeyboard());
        onView(withId(R.id.emailEditText)).perform(typeText("kapsalas_gi@yahoo.gr"),closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText("HELLOWOROLD"),closeSoftKeyboard());
        onView(withId(R.id.verifyPasswordEditText)).perform(typeText("HELLOWOROLD"),closeSoftKeyboard());
        onView(withId(R.id.registerAndLoginButton)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.map)).check(matches(isDisplayed()));

    }

    @Test
    public void putSomeCredentialsAndLoginExceptUsername_CheckIfUsernameGetsFocus() throws Exception{

        onView(withId(R.id.emailEditText)).perform(typeText("kapsalas_gi@yahoo.gr"),closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText("HELLOWOROLD"),closeSoftKeyboard());
        onView(withId(R.id.verifyPasswordEditText)).perform(typeText("HELLOWOROLD"),closeSoftKeyboard());
        onView(withId(R.id.registerAndLoginButton)).perform(click());
        onView(withId(R.id.usernameEditText)).check(matches(hasFocus()));

    }
    @Test
    public void putSomeCredentialsAndLoginExceptEmail_CheckIfEmailGetsFocus() throws Exception{

        onView(withId(R.id.usernameEditText)).perform(typeText("KAPPA123thisisaTESSSSST"),closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText("HELLOWOROLD"),closeSoftKeyboard());
        onView(withId(R.id.verifyPasswordEditText)).perform(typeText("HELLOWOROLD"),closeSoftKeyboard());
        onView(withId(R.id.registerAndLoginButton)).perform(click());
        onView(withId(R.id.emailEditText)).check(matches(hasFocus()));

    }

    @Test
    public void putSomeCredentialsAndLoginExceptPassword_CheckIfPasswordGetsFocus() throws Exception{

        onView(withId(R.id.usernameEditText)).perform(typeText("KAPPA123thisisaTESSSSST"),closeSoftKeyboard());
        onView(withId(R.id.emailEditText)).perform(typeText("kapsalas_gi@yahoo.gr"),closeSoftKeyboard());
        onView(withId(R.id.verifyPasswordEditText)).perform(typeText("HELLOWOROLD"),closeSoftKeyboard());
        onView(withId(R.id.registerAndLoginButton)).perform(click());
        onView(withId(R.id.passwordEditText)).check(matches(hasFocus()));

    }
    @Test
    public void putSomeCredentialsAndLoginExceptVerifyPassword_CheckIfVerifyPasswordGetsFocus() throws Exception{

        onView(withId(R.id.usernameEditText)).perform(typeText("KAPPA123thisisaTESSSSST"),closeSoftKeyboard());
        onView(withId(R.id.emailEditText)).perform(typeText("kapsalas_gi@yahoo.gr"),closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText("HELLOWOROLD"),closeSoftKeyboard());
        onView(withId(R.id.registerAndLoginButton)).perform(click());
        onView(withId(R.id.verifyPasswordEditText)).check(matches(hasFocus()));

    }
    @Test
    public void putCredentialsButUsernameContainsWrongCharacters_CheckIfIsernameGetsFcus() throws Exception {
        onView(withId(R.id.usernameEditText)).perform(typeText("Tralalo123456778!!WOWHELLO"), closeSoftKeyboard());
        onView(withId(R.id.emailEditText)).perform(typeText("kapsalas_gi@yahoo.gr"), closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText("HELLOWOROLD"), closeSoftKeyboard());
        onView(withId(R.id.verifyPasswordEditText)).perform(typeText("HELLOWOROLD"), closeSoftKeyboard());
        onView(withId(R.id.registerAndLoginButton)).perform(click());
        onView(withId(R.id.usernameEditText)).check(matches(hasFocus()));
    }

    @Test
    public void putCredentialsButEmailHasNotProperForm_CheckIfUsernameGetsFocus() throws Exception {
        onView(withId(R.id.usernameEditText)).perform(typeText("Tralalo123456778WOWHELLO"), closeSoftKeyboard());
        onView(withId(R.id.emailEditText)).perform(typeText("asd@asd.asdas"), closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText("HELLOWOROLD"), closeSoftKeyboard());
        onView(withId(R.id.verifyPasswordEditText)).perform(typeText("HELLOWOROLD"), closeSoftKeyboard());
        onView(withId(R.id.registerAndLoginButton)).perform(click());
        onView(withId(R.id.emailEditText)).check(matches(hasFocus()));
    }
    @Test
    public void putCredentialsButPasswordsAreDifferent_CheckIfVerifyPasswordGetsFocus() throws Exception {
        onView(withId(R.id.usernameEditText)).perform(typeText("Tralalo123456778WOWHELLO"), closeSoftKeyboard());
        onView(withId(R.id.emailEditText)).perform(typeText("asd@asd.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText("HELLOWOROLD"), closeSoftKeyboard());
        onView(withId(R.id.verifyPasswordEditText)).perform(typeText("1111111111"), closeSoftKeyboard());
        onView(withId(R.id.registerAndLoginButton)).perform(click());
        onView(withId(R.id.verifyPasswordEditText)).check(matches(hasFocus()));
    }
}
