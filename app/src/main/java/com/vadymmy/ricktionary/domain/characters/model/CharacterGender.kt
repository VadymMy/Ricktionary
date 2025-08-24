package com.vadymmy.ricktionary.domain.characters.model

enum class CharacterGender(val key: Int) {
    Male(key = 0),
    Female(key = 1),
    Genderless(key = 2),
    Unknown(key = 3);

    companion object {
        fun fromKey(key: Int) = entries.find { it.key == key } ?: Unknown
    }
}
