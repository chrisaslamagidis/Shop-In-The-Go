package com.sirialkillers.shoponthego;

import android.app.Activity;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Giannis on 3/11/2017.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void clickSignUpButton_CheckIfSignUpActivityIsDisplayed() throws Exception{
        onView(withId(R.id.signUpTextView)).perform(click());
        onView(withId(R.id.goBackToLoginTextView)).check(matches(isDisplayed()));
    }
    @Test
    public void clickSignUpButton_clickHaveAnAccountButton_CheckIfTheLoginActivityIsDiplayed() throws Exception{
        onView(withId(R.id.signUpTextView)).perform(click());
        onView(withId(R.id.goBackToLoginTextView)).perform(click());
        onView(withId(R.id.signUpTextView)).check(matches(isDisplayed()));
    }
    @Test
    public void clickLoginButton_DoesNothing() throws Exception{
        onView(withId(R.id.manualLoginButton)).perform(click());
        onView(withId(R.id.manualLoginButton)).check(matches(isDisplayed()));
    }
    @Test
    public void goToSignUp_tryToSignUpWithNoInput_DoesNotChangeActivity() throws Exception{
        onView(withId(R.id.signUpTextView)).perform(click());
        onView(withId(R.id.registerAndLoginButton)).perform(click());
        onView(withId(R.id.goBackToLoginTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void typeSomeEmailButNotPassword_CheckIfPasswordGetsFocus() throws Exception {
        onView(withId(R.id.emailEditText)).perform(typeText("kapsalas_gi@yahoo.gr"),closeSoftKeyboard());
        onView(withId(R.id.manualLoginButton)).perform(click());
        onView(withId(R.id.passwordEditText)).check(matches(hasFocus()));
    }

    @Test
    public void typeSomePasswordButNotEmail_CheckIfEmailGetsFocus() throws Exception {
        onView(withId(R.id.passwordEditText)).perform(typeText("asasd2132123"),closeSoftKeyboard());
        onView(withId(R.id.manualLoginButton)).perform(click());
        onView(withId(R.id.emailEditText)).check(matches(hasFocus()));
    }
    @Test
    public void LoginWithNormalCredentials_CheckIfMapIsDisplayed() throws Exception {
        onView(withId(R.id.emailEditText)).perform(typeText("kapsalas_gi@yahoo.gr"),closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText("asasd2132123"),closeSoftKeyboard());
        onView(withId(R.id.manualLoginButton)).perform(click());
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }
}
