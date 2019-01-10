package com.example.alejandro.cashsender.domain.interactor

import android.content.ContentResolver
import com.example.alejandro.cashsender.domain.model.Contact

interface GetPhoneContactsInteractor {
    fun execute(resolver: ContentResolver, onComplete: (List<Contact>) -> Unit, oError: (Throwable) -> Unit)
}