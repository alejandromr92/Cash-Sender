package com.example.alejandro.cashsender.network.api.impl

import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.network.Constants
import com.example.alejandro.cashsender.network.api.MarvelAPI
import com.example.alejandro.cashsender.network.interceptor.NonSecurityInterceptor
import com.example.alejandro.cashsender.network.model.dto.CharactersRequest
import com.example.alejandro.cashsender.network.model.mapper.MarvelCharactersMapper
import com.example.alejandro.cashsender.network.service.Endpoints
import com.example.alejandro.cashsender.network.service.MarvelService
import com.example.alejandro.cashsender.network.util.RetrofitClient
import io.reactivex.Single
class MarvelAPIImpl: MarvelAPI {
    private val service: MarvelService =  RetrofitClient.getMarvelService(Endpoints.BASE_URL, NonSecurityInterceptor())

    override fun getCharacters(request: CharactersRequest): Single<List<Contact>> {
        val request = CharactersRequest()
        return service.getMarvelCharacters(request.apikey, request.ts, request.hash, Constants.CHARACTERS_LIMIT).map { MarvelCharactersMapper.map(it.data.results) }
    }
}

