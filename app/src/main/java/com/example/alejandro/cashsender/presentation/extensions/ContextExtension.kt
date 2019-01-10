package com.example.alejandro.cashsender.presentation.extensions

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.presentation.Constants
import com.example.alejandro.cashsender.presentation.ui.activities.AmountActivity
import com.example.alejandro.cashsender.presentation.ui.activities.SummaryActivity
import java.util.ArrayList

fun Context.AmountIntent(contactsSelected: List<Contact>): Intent {
    return Intent(this, AmountActivity::class.java).apply {
        putParcelableArrayListExtra(Constants.SELECTED_CONTACTS_KEY, contactsSelected as ArrayList<out Parcelable>)
    }
}

fun Context.SummaryIntent(contactsSelected: List<Contact>, amount: Double): Intent {
    return Intent(this, SummaryActivity::class.java).apply {
        putExtra(Constants.AMOUNT_KEY, amount)
        putParcelableArrayListExtra(Constants.SELECTED_CONTACTS_KEY, contactsSelected as ArrayList<out Parcelable>)
    }
}