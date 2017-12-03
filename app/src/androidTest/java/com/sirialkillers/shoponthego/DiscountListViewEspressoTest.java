package com.sirialkillers.shoponthego;

import android.support.test.espresso.Espresso;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by User on 19-Nov-17.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class DiscountListViewEspressoTest {

    @Rule
    public ActivityTestRule<DiscountListView> mActivityTestRule = new ActivityTestRule<>(DiscountListView.class);


    @Test
    public void testThatComponentsAreThere(){
        Espresso.onView(withId(R.id.shopNameTextView)).check(matches(notNullValue()));
        Espresso.onView(withId(R.id.discountListView)).check(matches(notNullValue()));
    }

    @Test
    public void testThatListIsClickable(){
        Espresso.onData(anything()).inAdapterView(withContentDescription("theLisd")).atPosition(1).perform(click());
        intended(hasComponent(DiscountDetailsActivity.class.getName()));
    }
}
