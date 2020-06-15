package com.example.linkingyou;

import android.app.Activity;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class RegisterActivityTest {

    @Rule
    public
    ActivityTestRule<RegisterActivity> registerActivityActivityTestRule = new ActivityTestRule<RegisterActivity>(RegisterActivity.class);

    // To test the username editText
    @Test
    public void usernameText() {
        onView(withId(R.id.edUserReg)).perform(typeText("Jade"));
    }

    // To test the student number editText
    @Test
    public void studNoText() {
        onView(withId(R.id.Stud_id)).perform(typeText("123456"));
    }

    // To test the password editText
    @Test
    public void passText() {
        onView(withId(R.id.edPassReg)).perform(typeText("hey"));
    }

    // To test the confirm password editText
    @Test
    public void passConText() {
        onView(withId(R.id.edPassConReg)).perform(typeText("hey"));
    }

    // To test the back button
    @Test
    public void backButton(){
        onView(withId(R.id.btnBack1)).perform(click());
    }

    // To test the register button

    @Test
    public void regButton(){
        onView(withId(R.id.edUserReg)).perform(typeText("Jade"));
        onView(withId(R.id.Stud_id)).perform(typeText("Jade"));
        onView(withId(R.id.edPassReg)).perform(typeText("hey"));
        onView(withId(R.id.edPassConReg)).perform(typeText("hey"));
        onView(withId(R.id.btnReg)).perform(click());
    }
}