package com.example.alejandro.cashsender.view

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.alejandro.cashsender.R
import com.example.alejandro.cashsender.presentation.ui.activities.AmountActivity
import org.junit.Before
import org.junit.Test

class AmountActivityTest: AcceptanceTest<AmountActivity>(AmountActivity::class.java) {

    @Before
    fun setup(){
        testRule.launchActivity(Intent())
    }

    @Test
    fun test(){
        onView(withId(R.id.amount_input)).check(matches(isDisplayed()))

        onView(withId(R.id.amount_confirm_button)).check(matches(isDisplayed()))

        onView(withId(R.id.amount_input)).perform(click()).perform(typeText("1000.0"))

        onView(withId(R.id.amount_input)).check(matches(withText("1000.0")))
    }

}