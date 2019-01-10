package com.example.alejandro.cashsender.presentation.ui.activities

import android.os.Bundle
import com.example.alejandro.cashsender.R
import com.example.alejandro.cashsender.presentation.Constants
import com.example.alejandro.cashsender.presentation.extensions.SummaryIntent
import kotlinx.android.synthetic.main.activity_amount.*

class AmountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        this.layout = R.layout.activity_amount
        super.onCreate(savedInstanceState)

        this.contactsSelectedList = intent.getParcelableArrayListExtra(Constants.SELECTED_CONTACTS_KEY)
    }

    override fun configViews() {
        super.configViews()

        this.configNextBtn()
    }

    private fun configNextBtn(){
        amount_confirm_button.setOnClickListener {
            navigateToSummary()
        }
    }

    private fun obtainAmount(): Double {
        var amount =  (amount_input.text.toString()).toDouble()

        if (amount > Constants.MAX_AMOUNT){
            amount = Constants.MAX_AMOUNT
        } else if (amount < Constants.MIN_AMOUNT){
            amount = Constants.MIN_AMOUNT
        }

        amount = (amount.toString()).format("%.2f").toDouble()
        amount_input.setText(amount.toString())
        return amount
    }

    private fun navigateToSummary(){
        startActivity(this.SummaryIntent(contactsSelectedList as MutableList, obtainAmount()))
    }
}