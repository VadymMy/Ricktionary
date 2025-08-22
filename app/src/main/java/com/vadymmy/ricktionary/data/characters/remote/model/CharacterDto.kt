package com.vadymmy.ricktionary.data.characters.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: CharacterStatusDto,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: CharacterGenderDto,
    @SerializedName("origin")
    val origin: CharacterOriginDto,
    @SerializedName("location")
    val location: CharacterLocationDto,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("episode")
    val episodes: List<String>,
    @SerializedName("url")
    val url: String,
    @SerializedName("created")
    val createdAt: String
)
