package com.vadymmy.ricktionary.ui.characters.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.vadymmy.ricktionary.domain.characters.usecase.GetCharacterUseCase
import com.vadymmy.ricktionary.ui.base.BaseViewModel
import com.vadymmy.ricktionary.ui.navigation.model.AppNavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase
) : BaseViewModel<CharacterDetailsUiState, CharacterDetailsIntent, Nothing>(CharacterDetailsUiState()) {

    init {
        val characterId = savedStateHandle.toRoute<AppNavRoute.CharacterDetails>().id
        loadCharacter(characterId = characterId)
    }

    override fun reduceIntent(intent: CharacterDetailsIntent) {
        // TODO reduce details intent
    }

    private fun loadCharacter(characterId: Int) {
        updateUiState {
            it.copy(isLoading = true)
        }

        launchViewModelScope {
            val characterResult = getCharacterUseCase(parameters = characterId)

            updateUiState {
                it.copy(
                    isLoading = false,
                    character = characterResult.getOrNull(),
                    showLoadingError = characterResult.isFailure
                )
            }
        }
    }
}
