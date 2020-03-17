package com.example.linkingyou;

import android.view.View;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public
    ActivityTestRule<MainActivity> registerActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    // To test the person number editText
    @Test
    public void usernameText() {
        onView(withId(R.id.editText)).perform(typeText("123456"));
    }

    // To test the password
    @Test
    public void passText(){
        onView(withId(R.id.edPass)).perform(typeText("hey"));
    }

    // To test the link to the register page
    @Test
    public void regLink(){
        onView(withId(R.id.txtReg)).perform(click());
    }

    // To test the login
    @Test
    public void login(){
        onView(withId(R.id.editText)).perform(typeText("123456"));
        onView(withId(R.id.edPass)).perform(typeText("hey"));
        onView(withId(R.id.btnLog)).perform(click());

    }

    /* To test the function process login
    @Test
    public void processLogTest(){
        processlogin("1");
    }*/
}