package com.vadymmy.ricktionary.data.characters.remote.model

import com.google.gson.annotations.SerializedName

enum class CharacterStatusDto {
    @SerializedName("Alive")
    Alive,
    @SerializedName("Dead")
    Dead,
    @SerializedName("unknown")
    Unknown
}
