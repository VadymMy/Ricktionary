package com.vadymmy.ricktionary.data.characters.remote.model

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("info")
    val info: CharacterResponseInfo,
    @SerializedName("results")
    val characters: List<CharacterDto>
)
