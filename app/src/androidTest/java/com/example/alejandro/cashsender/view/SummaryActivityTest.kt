package com.example.alejandro.cashsender.view

import android.content.Intent
import android.os.Parcelable
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.example.alejandro.cashsender.R
import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.presentation.Constants
import com.example.alejandro.cashsender.presentation.ui.activities.SummaryActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SummaryActivityTest : AcceptanceTest<SummaryActivity>(SummaryActivity::class.java){

    private lateinit var contact: Contact

    private lateinit var contactsSelectedList: MutableList<Contact>

    private lateinit var intent: Intent

    private val amount = 1000.0

    @Before
    fun setup(){
        /**
         * Initialize data passed through Intent and start activity
         */
        contact = Contact(name = "Alejandro", thumbnail = "", phoneNumber = "666 666 666")
        contactsSelectedList = ArrayList()
        contactsSelectedList.add(contact)

        intent = Intent()
        intent.putExtra(Constants.AMOUNT_KEY, amount)
        intent.putParcelableArrayListExtra(Constants.SELECTED_CONTACTS_KEY, contactsSelectedList as java.util.ArrayList<out Parcelable>)

        testRule.launchActivity(intent)
    }

    @Test
    fun test(){
        onView(withId(R.id.amount_total)).check(matches(withText("1000.0")))
    }
}