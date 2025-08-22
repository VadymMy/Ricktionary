package com.vadymmy.ricktionary.domain.base.result

suspend fun <T> useResultWrapper(
    action: suspend () -> T
): ResultObject<T> = try {
    val actionResult = action()
    ResultObject.Success(actionResult)
} catch (t: Throwable) {
    ResultObject.Error(t)
}
