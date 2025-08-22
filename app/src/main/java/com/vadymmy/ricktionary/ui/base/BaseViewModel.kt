package com.vadymmy.ricktionary.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, INTENT, EFFECT>(
    initialState: STATE
) : ViewModel() {
    protected abstract fun reduceIntent(intent: INTENT)

    val uiStateFlow: StateFlow<STATE> = MutableStateFlow(initialState)
    val uiEffectFlow: SharedFlow<EFFECT> = MutableSharedFlow()
    protected val uiState get() = uiStateFlow.value

    open fun onResume() = Unit

    fun onUserIntent(intent: INTENT) = reduceIntent(intent)
    protected fun updateUiState(update: (STATE) -> STATE) = (uiStateFlow as MutableStateFlow).getAndUpdate(update)
    protected fun sendUiEffect(effect: EFFECT) = launchViewModelScope { (uiEffectFlow as MutableSharedFlow).emit(effect) }
    protected fun launchViewModelScope(action: suspend () -> Unit) = viewModelScope.launch { action() }
}
