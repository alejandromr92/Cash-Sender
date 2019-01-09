package com.example.alejandro.cashsender.presentation.presenter

import com.example.alejandro.cashsender.domain.model.Contact

interface GetMarvelCharactersPresenter {
    fun getMarvelCharacters()

    interface View: BaseView {
        fun onMarvelCharactersRetrieved(marvelCharactersList: List<Contact>)
        fun onMarvelCharactersRetrievingError()
    }
}