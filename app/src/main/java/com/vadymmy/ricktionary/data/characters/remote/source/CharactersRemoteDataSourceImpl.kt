package com.vadymmy.ricktionary.data.characters.remote.source

import com.vadymmy.ricktionary.data.characters.remote.api.CharactersApi
import javax.inject.Inject

class CharactersRemoteDataSourceImpl @Inject constructor(
    private val charactersApi: CharactersApi
) : CharactersRemoteDataSource {

    override suspend fun getCharacters() = charactersApi.getCharacters()

    override suspend fun getCharacter(id: Int) = charactersApi.getCharacter(id = id)
}
