package com.example.linkingyou;

import android.app.Activity;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class TabActivityTest {

    @Rule
    public
    ActivityTestRule<TabActivity> tabActivityActivityTestRule = new ActivityTestRule<TabActivity>(TabActivity.class);

    // Test the create button
    @Test
    public void createButton(){
        onView(withId(R.id.ButtonCreate));
    }

    // Tests the add button
    @Test
    public void addGroup(){
        onView(withId(R.id.fab)).perform(click());
    }
}