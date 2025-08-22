package com.vadymmy.ricktionary.domain.characters.usecase

import com.vadymmy.ricktionary.domain.base.usecase.BaseNoParamsUseCase
import com.vadymmy.ricktionary.domain.characters.CharactersRepository
import com.vadymmy.ricktionary.domain.characters.model.Character
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class GetCharactersFlowUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
): BaseNoParamsUseCase<Flow<List<Character>>>(){

    override suspend fun execute() = charactersRepository.charactersFlow
}
