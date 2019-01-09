package com.example.alejandro.cashsender.network.service

import com.example.alejandro.cashsender.network.Constants
import com.example.alejandro.cashsender.network.model.dto.CharactersRequest
import com.example.alejandro.cashsender.network.model.dto.MarvelCharactersDto
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MarvelService {

    @GET(Endpoints.CHARACTERS)
    fun getMarvelCharacters(
        @Query(Constants.API_KEY) publicKey: String,
        @Query(Constants.TIMESTAMP) timestamp: String,
        @Query(Constants.HASH) hash: String,
        @Query(Constants.LIMIT) limit: Int
    ) : Single<MarvelCharactersDto>
}