package com.inspire_hub.shortstories.core.domain

sealed interface Result<out T> {
    data class Success<T>(val data:T): Result<T>
    data class Error<T>(val t:Throwable): Result<T>
    data object Loading: Result<Nothing>
}