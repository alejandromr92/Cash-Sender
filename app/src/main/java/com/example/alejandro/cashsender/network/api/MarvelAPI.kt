package com.example.alejandro.cashsender.network.api

import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.network.model.dto.CharactersRequest
import io.reactivex.Single

interface MarvelAPI {
    fun getCharacters(request: CharactersRequest): Single<List<Contact>>
}