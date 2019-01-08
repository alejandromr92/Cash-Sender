package com.example.alejandro.cashsender.network.model.dto

data class MarvelCharactersDto (
    val code: Int,
    val status: String,
    val data: CharactersDataDto
)

data class CharactersDataDto(
    val results: List<CharactersDataResultsDto>
)

data class CharactersDataResultsDto(
    val name: String,
    val thumbnail: CharacterThumbnailDto
)

data class CharacterThumbnailDto(
    val path: String,
    val extension: String
)