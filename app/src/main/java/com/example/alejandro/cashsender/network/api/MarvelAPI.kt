package com.example.alejandro.cashsender.network.api

import com.example.alejandro.cashsender.domain.model.model.MarvelCharacter
import com.example.alejandro.cashsender.network.model.dto.CharactersRequest
import com.example.alejandro.cashsender.network.model.dto.MarvelCharactersDto
import io.reactivex.Single

interface MarvelAPI {
    fun getCharacters(request: CharactersRequest): Single<List<MarvelCharacter>>
}