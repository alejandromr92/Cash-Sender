package com.example.alejandro.cashsender.network.model.mapper

import com.example.alejandro.cashsender.domain.model.model.MarvelCharacter
import com.example.alejandro.cashsender.network.model.dto.CharactersDataResultsDto

abstract class MarvelCharactersMapper {
    companion object {
        fun map(charactersList: List<CharactersDataResultsDto>): List<MarvelCharacter> {
            val list = ArrayList<MarvelCharacter>()

            if (charactersList.isNotEmpty()){
                for (c in charactersList){
                    list.add(map(c))
                }
            }

            return list
        }

        fun map(dto: CharactersDataResultsDto) = MarvelCharacter(dto.name, dto.thumbnail.path + dto.thumbnail.extension)
    }
}