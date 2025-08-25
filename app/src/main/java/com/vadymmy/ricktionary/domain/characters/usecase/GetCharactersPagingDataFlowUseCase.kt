package com.vadymmy.ricktionary.domain.characters.usecase

import androidx.paging.PagingData
import com.vadymmy.ricktionary.domain.base.usecase.BaseNoParamsFlowUseCase
import com.vadymmy.ricktionary.domain.characters.CharactersRepository
import com.vadymmy.ricktionary.domain.characters.model.Character
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCharactersPagingDataFlowUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) : BaseNoParamsFlowUseCase<PagingData<Character>>() {

    override fun execute() = charactersRepository.charactersPagingDataFlow
}
