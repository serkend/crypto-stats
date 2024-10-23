package com.helloworld.common.utils

sealed interface ResultState<out D, out E: NetworkError> {
    data class Success<out D>(val data: D): ResultState<D, Nothing>
    data class Error<out E: NetworkError>(val error: E): ResultState<Nothing, E>
}

inline fun <D, E:NetworkError> ResultState<D, E>.onSuccess(action: (D) -> ResultState<D,E>): ResultState<D, E> {
    return when(this) {
        is ResultState.Success -> action(this.data)
        is ResultState.Error -> this
    }
}

inline fun <D, E:NetworkError> ResultState<D, E>.onFailure(action: (E) -> ResultState<D,E>): ResultState<D, E> {
    return when(this) {
        is ResultState.Success -> this
        is ResultState.Error -> action(this.error)
    }
}

inline fun <T, E: NetworkError, R> ResultState<T, E>.map(map: (T) -> R): ResultState<R, E> {
    return when(this) {
        is ResultState.Error -> ResultState.Error(error)
        is ResultState.Success -> ResultState.Success(map(data))
    }
}
