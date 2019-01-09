package com.example.alejandro.cashsender.network.model.mapper

import com.example.alejandro.cashsender.domain.model.Contact
import com.example.alejandro.cashsender.network.model.dto.CharactersDataResultsDto

abstract class MarvelCharactersMapper {
    companion object {
        fun map(charactersList: List<CharactersDataResultsDto>): List<Contact> {
            val list = ArrayList<Contact>()

            if (charactersList.isNotEmpty()){
                for (c in charactersList){
                    list.add(map(c))
                }
            }

            return list
        }

        fun map(dto: CharactersDataResultsDto) = Contact(
            dto.name,
            dto.thumbnail.path + "." + dto.thumbnail.extension
        )
    }
}