package com.vadymmy.ricktionary.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadymmy.ricktionary.domain.base.CoroutineLauncher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate

abstract class BaseViewModel<STATE>(
    initialState: STATE
) : ViewModel(), CoroutineLauncher {
    override val scope: CoroutineScope = viewModelScope

    val uiStateFlow: StateFlow<STATE> = MutableStateFlow(initialState)

    protected val uiState get() = uiStateFlow.value
    protected fun updateUiState(update: (STATE) -> STATE) = (uiStateFlow as MutableStateFlow).getAndUpdate(update)
}
