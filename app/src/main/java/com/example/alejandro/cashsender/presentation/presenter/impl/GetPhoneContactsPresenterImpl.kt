package com.example.alejandro.cashsender.presentation.presenter.impl

import android.content.ContentResolver
import com.example.alejandro.cashsender.domain.interactor.impl.GetPhoneContactsInteractorImpl
import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.presentation.presenter.GetPhoneContactsPresenter
import io.reactivex.Scheduler

class GetPhoneContactsPresenterImpl(
    private val threadExecutor: Scheduler,
    private val mainThread: Scheduler,
    private val view: GetPhoneContactsPresenter.View
): GetPhoneContactsPresenter {

    override fun getPhoneContacts(resolver: ContentResolver) {
        view.showProgress()
        val interactor = GetPhoneContactsInteractorImpl(threadExecutor, mainThread)
        interactor.execute(resolver, ::onPhoneContactsRetrieved, ::onPhoneContactsRetrievingError)
    }

    private fun onPhoneContactsRetrieved(phoneContacts: List<Contact>){
        view.hideProgress()
        view.onPhoneContactsRetrieved(phoneContacts)

    }
    private fun onPhoneContactsRetrievingError(throwable: Throwable){
        view.hideProgress()
        view.onPhoneContactsRetrievingError()
    }


}