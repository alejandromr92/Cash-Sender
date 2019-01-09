package com.example.alejandro.cashsender.domain.interactor

import com.example.alejandro.cashsender.domain.model.Contact

interface GetMarvelCharactersInteractor {
    fun execute(onComplete: (List<Contact>) -> Unit, onError: (Throwable) -> Unit)
}