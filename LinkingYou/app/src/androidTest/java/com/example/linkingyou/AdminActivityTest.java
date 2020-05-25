package com.example.linkingyou;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class AdminActivityTest {

    @Rule
    public
    ActivityTestRule<AdminActivity> adminActivityActivityTestRule = new ActivityTestRule<AdminActivity>(AdminActivity.class);

    // Test accept button
   /* @Test
    public void acceptButton(){
        onView(withId(R.id.btnAccept));
    }*/


}