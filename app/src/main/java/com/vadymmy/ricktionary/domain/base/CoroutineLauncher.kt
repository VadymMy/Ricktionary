package com.vadymmy.ricktionary.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface CoroutineLauncher {
    val scope: CoroutineScope

    fun launchCoroutine(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        onError: (Throwable) -> Unit = {},
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return scope.launch {
            withContext(dispatcher) {
                runCatching {
                    block()
                }.onFailure {
                    onError(it)
                }
            }
        }
    }
}
