package com.vadymmy.ricktionary.data.characters.remote.api

import com.vadymmy.ricktionary.data.characters.remote.model.CharacterDto
import com.vadymmy.ricktionary.data.characters.remote.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharactersResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDto
}
