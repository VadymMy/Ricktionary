package com.vadymmy.ricktionary.data.characters.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterResponseInfo(
    @SerializedName("next")
    val nextPageUrl: String?,
    @SerializedName("prev")
    val previousPageUrl: String?
)
