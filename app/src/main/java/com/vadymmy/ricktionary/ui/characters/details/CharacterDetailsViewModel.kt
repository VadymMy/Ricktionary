package com.vadymmy.ricktionary.ui.characters.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.vadymmy.ricktionary.domain.characters.usecase.GetCharacterUseCase
import com.vadymmy.ricktionary.ui.base.BaseViewModel
import com.vadymmy.ricktionary.ui.characters.common.mapper.toUiModel
import com.vadymmy.ricktionary.ui.navigation.AppNavigator
import com.vadymmy.ricktionary.ui.navigation.model.AppNavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val appNavigator: AppNavigator
) : BaseViewModel<CharacterDetailsUiState, CharacterDetailsIntent, Nothing>(CharacterDetailsUiState()) {
    private val characterId = savedStateHandle.toRoute<AppNavRoute.CharacterDetails>().id

    init {
        loadCharacter(characterId = characterId)
    }

    override fun reduceIntent(intent: CharacterDetailsIntent) {
        launchViewModelScope {
            when (intent) {
                CharacterDetailsIntent.BackButtonClicked -> appNavigator.back()
                CharacterDetailsIntent.RetryButtonClicked -> loadCharacter(characterId = characterId)
            }
        }
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
                    character = characterResult.getOrNull()?.toUiModel(),
                    showLoadingError = characterResult.isFailure
                )
            }
        }
    }
}
