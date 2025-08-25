package com.vadymmy.ricktionary.ui.characters.list

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vadymmy.ricktionary.domain.characters.usecase.GetCharactersPagingDataFlowUseCase
import com.vadymmy.ricktionary.ui.base.BaseViewModel
import com.vadymmy.ricktionary.ui.characters.common.mapper.toItemUiModels
import com.vadymmy.ricktionary.ui.navigation.AppNavigator
import com.vadymmy.ricktionary.ui.navigation.model.AppNavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    getCharactersPagingDataFlowUseCase: GetCharactersPagingDataFlowUseCase,
    private val appNavigator: AppNavigator
) : BaseViewModel<CharactersUiState, CharactersIntent, Nothing>(CharactersUiState()) {

    val charactersFlow = getCharactersPagingDataFlowUseCase().map { it.toItemUiModels() }.cachedIn(viewModelScope)

    override fun reduceIntent(intent: CharactersIntent) {
        launchViewModelScope {
            when (intent) {
                is CharactersIntent.CharacterItemClicked -> {
                    appNavigator.navigateTo(AppNavRoute.CharacterDetails(id = intent.character.id))
                }
            }
        }
    }
}
