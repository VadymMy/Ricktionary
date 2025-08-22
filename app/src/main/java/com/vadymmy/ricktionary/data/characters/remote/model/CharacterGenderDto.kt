package com.vadymmy.ricktionary.data.characters.remote.model

import com.google.gson.annotations.SerializedName

enum class CharacterGenderDto {
    @SerializedName("Male")
    Male,
    @SerializedName("Female")
    Female,
    @SerializedName("Genderless")
    Genderless,
    @SerializedName("unknown")
    Unknown
}
