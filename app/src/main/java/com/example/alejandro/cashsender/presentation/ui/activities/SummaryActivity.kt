package com.example.alejandro.cashsender.presentation.ui.activities

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.alejandro.cashsender.R
import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.presentation.Constants
import com.example.alejandro.cashsender.presentation.extensions.ContactsSelectionIntent
import com.example.alejandro.cashsender.presentation.ui.adapters.ContactsListAdapter
import kotlinx.android.synthetic.main.activity_summary.*
import org.jetbrains.anko.toast
import java.util.ArrayList

class SummaryActivity : BaseActivity() {

    private var amount: Double = 0.00

    private var contactsListAdapter: ContactsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        this.layout = R.layout.activity_summary
        super.onCreate(savedInstanceState)

        this.contactsSelectedList = intent.getParcelableArrayListExtra(Constants.SELECTED_CONTACTS_KEY)
        this.amount = intent.getDoubleExtra(Constants.AMOUNT_KEY, 0.00)
    }

    override fun configViews() {
        super.configViews()

        this.configAmount()

        this.configNextBtn()

        this.configRecyclerView()
    }

    private fun configAmount(){
        this.amount_total.text = amount.toString()
    }

    private fun configNextBtn(){
        confirm_operation_btn.setOnClickListener {
            toast(getString(R.string.transference_done))
            startActivity(ContactsSelectionIntent())
        }
    }

    private fun configRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        contacts_confirmation_list.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(contacts_confirmation_list.context, layoutManager.orientation)
        contacts_confirmation_list.addItemDecoration(dividerItemDecoration)

        this.contactsListAdapter = ContactsListAdapter(contactsSelectedList as ArrayList<Contact>, null, amount/ (contactsSelectedList as ArrayList<Contact>).size)
        contacts_confirmation_list.adapter = contactsListAdapter
    }

}