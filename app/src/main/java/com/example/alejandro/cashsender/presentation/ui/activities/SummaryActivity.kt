package com.example.alejandro.cashsender.presentation.ui.activities

import android.os.Bundle
import com.example.alejandro.cashsender.R
import com.example.alejandro.cashsender.presentation.Constants

class SummaryActivity : BaseActivity() {

    private var amount: Double = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        this.layout = R.layout.activity_confirm
        super.onCreate(savedInstanceState)

        this.contactsSelectedList = intent.getParcelableArrayListExtra(Constants.SELECTED_CONTACTS_KEY)
        this.amount = intent.getDoubleExtra(Constants.AMOUNT_KEY, 0.00)
    }
}