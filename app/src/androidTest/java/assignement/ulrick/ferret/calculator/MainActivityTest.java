package assignement.ulrick.ferret.calculator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickTouchTest() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1")));
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("12")));
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("123")));
        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1234")));
        onView(withId(R.id.button5)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("12345")));
        onView(withId(R.id.button6)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("123456")));
        onView(withId(R.id.button7)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1234567")));
        onView(withId(R.id.button8)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("12345678")));
        onView(withId(R.id.button9)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("123456789")));
        onView(withId(R.id.button0)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1234567890")));
        onView(withId(R.id.buttonParO)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1234567890(")));
        onView(withId(R.id.buttonParC)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1234567890()")));
        onView(withId(R.id.buttonPlus)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1234567890()+")));
        onView(withId(R.id.buttonMinus)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1234567890()+-")));
        onView(withId(R.id.buttonMult)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1234567890()+-*")));
        onView(withId(R.id.buttonDiv)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1234567890()+-*/")));
        onView(withId(R.id.clear)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("")));
    }

    @Test
    public void calculateExpression(){
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.buttonPlus)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("1+2")));
        onView(withId(R.id.calculate)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("3")));
        onView(withId(R.id.buttonMinus)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("-2")));
        onView(withId(R.id.calculate)).perform(click());
        onView(withId(R.id.result)).check(matches(withText("wrong expression")));

    }
}
