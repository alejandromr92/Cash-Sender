package com.example.alejandro.cashsender.network.model.dto

import com.example.alejandro.cashsender.network.Constants
import com.example.alejandro.cashsender.network.util.NetworkUtils

data class CharactersRequest(
    val apikey: String = Constants.PUBLIC_KEY,
    val ts: String = (System.currentTimeMillis()/Constants.MILIS).toString(),
    val hash: String = NetworkUtils.Companion.generateMd5(ts,Constants.PRIVATE_KEY, Constants.PUBLIC_KEY),
    val limit: Int = Constants.CHARACTERS_LIMIT
)