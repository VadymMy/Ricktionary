package com.vadymmy.ricktionary.ui.characters.list

import com.vadymmy.ricktionary.domain.characters.usecase.FetchCharactersUseCase
import com.vadymmy.ricktionary.domain.characters.usecase.GetCharactersFlowUseCase
import com.vadymmy.ricktionary.ui.base.BaseViewModel
import com.vadymmy.ricktionary.ui.characters.common.mapper.toItemUiModels
import com.vadymmy.ricktionary.ui.navigation.AppNavigator
import com.vadymmy.ricktionary.ui.navigation.model.AppNavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersFlowUseCase: GetCharactersFlowUseCase,
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val appNavigator: AppNavigator
) : BaseViewModel<CharactersUiState, CharactersIntent, Nothing>(CharactersUiState()) {

    init {
        observeCharactersFlow()
    }

    override fun onCreate() {
        if (uiState.characters.isEmpty()) {
            fetchCharacters()
        }
    }

    override fun reduceIntent(intent: CharactersIntent) {
        launchViewModelScope {
            when (intent) {
                is CharactersIntent.CharacterItemClicked -> {
                    appNavigator.navigateTo(AppNavRoute.CharacterDetails(id = intent.character.id))
                }
            }
        }
    }

    private fun observeCharactersFlow() {
        launchViewModelScope {
            getCharactersFlowUseCase().collectLatest { characters ->
                updateUiState {
                    it.copy(characters = characters.toItemUiModels())
                }
            }
        }
    }

    private fun fetchCharacters() {
        updateUiState {
            it.copy(isLoading = true)
        }

        launchViewModelScope {
            val fetchResult = fetchCharactersUseCase()

            updateUiState {
                it.copy(
                    isLoading = false,
                    showLoadingError = fetchResult.isFailure
                )
            }
        }
    }
}
