package com.vadymmy.ricktionary.data.characters.remote.api

import com.vadymmy.ricktionary.data.characters.remote.model.CharactersResponse
import retrofit2.http.GET

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters() : CharactersResponse
}
