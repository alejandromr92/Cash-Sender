package com.example.alejandro.cashsender.view

import android.content.Intent
import android.os.SystemClock.sleep
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import com.example.alejandro.cashsender.R
import com.example.alejandro.cashsender.presentation.ui.activities.ContactsActivity
import org.junit.Before
import org.junit.Test

class ContactsActivityTest: AcceptanceTest<ContactsActivity>(ContactsActivity::class.java) {

    @Before
    fun setup(){
        testRule.launchActivity(Intent())
    }

    @Test
    fun test(){
        sleep(3000)

        onView(ViewMatchers.withId(R.id.contacts_instructions)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.contacts_selection_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.confirm_contacts_selected_btn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}