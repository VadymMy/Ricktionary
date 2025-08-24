package com.vadymmy.ricktionary.domain.characters.usecase

import com.vadymmy.ricktionary.domain.base.result.useResultWrapper
import com.vadymmy.ricktionary.domain.base.usecase.BaseNoParamsUseCase
import com.vadymmy.ricktionary.domain.characters.CharactersRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class AreCharactersSavedLocallyUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) : BaseNoParamsUseCase<Result<Boolean>>() {
    override suspend fun execute() = useResultWrapper {
        charactersRepository.areCharactersSaved()
    }
}
