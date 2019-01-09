package com.example.alejandro.cashsender.presentation.presenter.impl

import com.example.alejandro.cashsender.domain.interactor.impl.GetMarvelCharactersInteractorImpl
import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.presentation.presenter.GetMarvelCharactersPresenter
import io.reactivex.Scheduler

class GetMarvelCharactersPresenterImpl(
    private val threadExecutor: Scheduler,
    private val mainThread: Scheduler,
    private val view: GetMarvelCharactersPresenter.View
) : GetMarvelCharactersPresenter{


    override fun getMarvelCharacters() {
        view.showProgress()
        val interactor = GetMarvelCharactersInteractorImpl(mainThread, threadExecutor)
        interactor.execute(::onMarvelCharactersRetrieved, ::onMarvelCharactersRetrievingError)
    }

    private fun onMarvelCharactersRetrieved(marvelCharactersList: List<Contact>){
        view.hideProgress()
        view.onMarvelCharactersRetrieved(marvelCharactersList)
    }

    private fun onMarvelCharactersRetrievingError(throwable: Throwable){
        view.hideProgress()
        view.onMarvelCharactersRetrievingError()
    }
}