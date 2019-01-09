package com.example.alejandro.cashsender.domain.interactor.impl

import com.example.alejandro.cashsender.domain.interactor.GetMarvelCharactersInteractor
import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.network.api.impl.MarvelAPIImpl
import com.example.alejandro.cashsender.network.model.dto.CharactersRequest
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposables

class GetMarvelCharactersInteractorImpl(
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
): GetMarvelCharactersInteractor {
    private val api = MarvelAPIImpl()

    private var subscription = Disposables.empty()

    override fun execute(onComplete: (List<Contact>) -> Unit, onError: (Throwable) -> Unit) {
        subscription = api.getCharacters(CharactersRequest())
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe(onComplete, onError)
    }
}