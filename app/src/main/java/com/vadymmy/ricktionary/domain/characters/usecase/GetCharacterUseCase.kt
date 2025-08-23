package com.vadymmy.ricktionary.domain.characters.usecase

import com.vadymmy.ricktionary.domain.base.result.useResultWrapper
import com.vadymmy.ricktionary.domain.base.usecase.BaseUseCase
import com.vadymmy.ricktionary.domain.characters.CharactersRepository
import com.vadymmy.ricktionary.domain.characters.model.Character
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCharacterUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) : BaseUseCase<Int, Result<Character>>() {

    override suspend fun execute(parameters: Int) = useResultWrapper {
        charactersRepository.getCharacter(id = parameters)
    }
}
