package com.vadymmy.ricktionary.data.characters.remote.source

import com.vadymmy.ricktionary.data.characters.remote.model.CharacterDto
import com.vadymmy.ricktionary.data.characters.remote.model.CharactersResponse

interface CharactersRemoteDataSource {

    suspend fun getCharacters(page: Int): CharactersResponse

    suspend fun getCharacter(id: Int): CharacterDto
}
