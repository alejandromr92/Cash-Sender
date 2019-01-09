package com.example.alejandro.cashsender

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.presentation.presenter.GetMarvelCharactersPresenter
import com.example.alejandro.cashsender.presentation.presenter.impl.GetMarvelCharactersPresenterImpl
import com.example.alejandro.cashsender.presentation.ui.activities.BaseActivity
import com.example.alejandro.cashsender.utils.LoggerUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContactsActivity : BaseActivity(), GetMarvelCharactersPresenter.View {
    private var getMarvelCharactersPresenter: GetMarvelCharactersPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        this.layout = R.layout.activity_contacts
        super.onCreate(savedInstanceState)
    }

    override fun initializePresenters() {
        super.initializePresenters()

        this.getMarvelCharactersPresenter = GetMarvelCharactersPresenterImpl(
            Schedulers.newThread(),
            AndroidSchedulers.mainThread(),
            this
        )
    }

    override fun loadData() {
        super.loadData()

        this.getMarvelCharactersPresenter!!.getMarvelCharacters()
    }

    override fun onMarvelCharactersRetrieved(marvelCharactersList: List<Contact>) {
        LoggerUtils.logMessage("CHARACTERS", "Success")
    }

    override fun onMarvelCharactersRetrievingError() {
        LoggerUtils.logMessage("CHARACTERS", "Error")
    }
}
