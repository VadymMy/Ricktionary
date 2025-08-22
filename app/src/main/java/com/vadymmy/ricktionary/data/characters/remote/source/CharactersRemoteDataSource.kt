package com.vadymmy.ricktionary.data.characters.remote.source

import com.vadymmy.ricktionary.data.characters.remote.model.CharactersResponse

interface CharactersRemoteDataSource {
    suspend fun getCharacters(): CharactersResponse
}
