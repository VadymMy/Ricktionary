package com.vadymmy.ricktionary.domain.characters.model

enum class CharacterStatus(val key: Int) {
    Alive(key = 0),
    Dead(key = 1),
    Unknown(key = 2);

    companion object {
        fun fromKey(key: Int) = entries.find { it.key == key } ?: Unknown
    }
}
