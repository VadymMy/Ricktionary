package com.vadymmy.ricktionary.ui.characters.list

import com.vadymmy.ricktionary.domain.base.result.ResultObject
import com.vadymmy.ricktionary.domain.characters.usecase.FetchCharactersUseCase
import com.vadymmy.ricktionary.domain.characters.usecase.GetCharactersFlowUseCase
import com.vadymmy.ricktionary.ui.base.BaseViewModel
import com.vadymmy.ricktionary.ui.characters.list.mapper.toUiModels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersFlowUseCase: GetCharactersFlowUseCase,
    private val fetchCharactersUseCase: FetchCharactersUseCase
) : BaseViewModel<CharactersUiState, CharactersIntent, Nothing>(CharactersUiState()) {

    init {
        observeCharactersFlow()
    }

    override fun onResume() {
        fetchCharacters()
    }

    override fun reduceIntent(intent: CharactersIntent) {
        // TODO reduce intent
    }

    private fun observeCharactersFlow() {
        launchViewModelScope {
            getCharactersFlowUseCase().collectLatest { characters ->
                updateUiState {
                    it.copy(characters = characters.toUiModels())
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
                    showLoadingError = fetchResult is ResultObject.Error
                )
            }
        }
    }
}
