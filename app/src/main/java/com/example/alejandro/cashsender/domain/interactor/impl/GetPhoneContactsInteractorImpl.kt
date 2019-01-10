package com.example.alejandro.cashsender.domain.interactor.impl

import android.content.ContentResolver
import android.provider.ContactsContract
import com.example.alejandro.cashsender.domain.interactor.GetPhoneContactsInteractor
import com.example.alejandro.cashsender.domain.model.Contact
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposables
import java.lang.Exception

class GetPhoneContactsInteractorImpl(
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
): GetPhoneContactsInteractor {

    private var subscription = Disposables.empty()

    override fun execute(resolver: ContentResolver, onComplete: (List<Contact>) -> Unit, oError: (Throwable) -> Unit) {
        subscription = retrieveContacts(resolver)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe(onComplete, oError)
    }

    private fun retrieveContacts(resolver: ContentResolver): Single<ArrayList<Contact>> {
        val phone_contacts = ArrayList<Contact>()

        val cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null)

        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val photo_uri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                val hasPhoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)).toInt() == 1

                if (hasPhoneNumber) {
                    val cursorPhone = resolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id), null)

                    if(cursorPhone.count > 0) {
                        while (cursorPhone.moveToNext()) {
                            val phoneNumValue = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            phone_contacts.add(Contact(name, photo_uri, phoneNumValue))
                        }
                    }
                    cursorPhone.close()
                }
            }
        }
        cursor.close()

        return Single.just(phone_contacts)
    }
}