package com.example.alejandro.cashsender.presentation.presenter

import android.content.ContentResolver
import com.example.alejandro.cashsender.domain.model.Contact

interface GetPhoneContactsPresenter {
    fun getPhoneContacts(resolver: ContentResolver)

    interface View: BaseView {
        fun onPhoneContactsRetrieved(phoneContacts: List<Contact>)
        fun onPhoneContactsRetrievingError()
    }
}