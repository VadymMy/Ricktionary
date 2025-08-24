package com.vadymmy.ricktionary.domain.characters

import androidx.paging.PagingData
import com.vadymmy.ricktionary.domain.characters.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    val charactersPagingDataFlow: Flow<PagingData<Character>>

    suspend fun areCharactersSaved(): Boolean

    suspend fun getCharacter(id: Int): Character
}
