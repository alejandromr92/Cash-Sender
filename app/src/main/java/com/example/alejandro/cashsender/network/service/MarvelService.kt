package com.example.alejandro.cashsender.network.service

import com.example.alejandro.cashsender.network.model.dto.CharactersRequest
import com.example.alejandro.cashsender.network.model.dto.MarvelCharactersDto
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MarvelService {

    @POST(Endpoints.CHARACTERS)
    fun getMarvelCharacters(
        @Body request: CharactersRequest
    ) : Single<MarvelCharactersDto>
}