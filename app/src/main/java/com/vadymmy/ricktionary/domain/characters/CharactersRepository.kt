package com.vadymmy.ricktionary.domain.characters

import com.vadymmy.ricktionary.domain.characters.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    val charactersFlow: Flow<List<Character>>

   suspend fun fetchCharacters()
}
