package com.vadymmy.ricktionary.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadymmy.ricktionary.domain.base.CoroutineLauncher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate

abstract class BaseViewModel<STATE, INTENT, EFFECT : Any>(
    initialState: STATE
) : ViewModel(), CoroutineLauncher {
    override val scope: CoroutineScope = viewModelScope
    protected abstract fun reduceIntent(intent: INTENT)

    val uiStateFlow: StateFlow<STATE> = MutableStateFlow(initialState)
    val uiEffectFlow: SharedFlow<EFFECT> = MutableSharedFlow()
    protected val uiState get() = uiStateFlow.value

    fun onUserIntent(intent: INTENT) = reduceIntent(intent)
    protected fun updateUiState(update: (STATE) -> STATE) = (uiStateFlow as MutableStateFlow).getAndUpdate(update)
    protected fun sendUiEffect(effect: EFFECT) = launchCoroutine { (uiEffectFlow as MutableSharedFlow).emit(effect) }
}
