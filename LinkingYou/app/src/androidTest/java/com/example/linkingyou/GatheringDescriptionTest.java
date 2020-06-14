package com.example.linkingyou;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class GatheringDescriptionTest {

    @Rule
    public
    ActivityTestRule<GatheringDescription> gatheringDescriptionActivityActivityTestRule =
            new ActivityTestRule<GatheringDescription>(GatheringDescription.class);

    // Test the group name
    @Test
    public void groupName(){
        onView(withId(R.id.editTxt)).perform(typeText("Robos"));
    }

    // Test the group description
    @Test
    public void groupDescription(){
        onView(withId(R.id.edDesc)).perform(typeText("Robotic club"));
    }

    // Test the club checkbox
    @Test
    public void clubCheckbox(){
        onView(withId(R.id.chkClub)).perform(click());
    }

    // Test the interest group checkbox
    @Test
    public void interestGroupCheckbox(){
        onView(withId(R.id.chkInterest)).perform(click());
    }

    // Test the society checkbox
    @Test
    public void societyCheckbox(){
        onView(withId(R.id.chkSocial)).perform(click());
    }

    // Button
    @Test
    public void groupDeatsButton() {
        onView(withId(R.id.btnConf)).perform(click());
    }

    // Test in unison
    @Test
    public void descriptionView(){
        onView(withId(R.id.editTxt)).perform(typeText("Robos"));
        onView(withId(R.id.edDesc)).perform(typeText("Robotic club"));
        onView(withId(R.id.chkClub)).perform(click());
        onView(withId(R.id.chkInterest)).perform(click());
        onView(withId(R.id.chkSocial)).perform(click());
        //onView(withId(R.id.btnConf)).perform(click());
    }

}