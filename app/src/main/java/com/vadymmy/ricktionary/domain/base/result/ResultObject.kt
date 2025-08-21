package com.vadymmy.ricktionary.domain.base.result

sealed class ResultObject<out T> {
    data class Success<T>(val value: T) : ResultObject<T>()
    data class Error(val errorCause: Throwable? = null) : ResultObject<Nothing>() {
        override fun toString(): String = errorCause?.toString() ?: "Unknown error"
    }

    companion object {
        fun success() = Success(Unit)
    }
}
