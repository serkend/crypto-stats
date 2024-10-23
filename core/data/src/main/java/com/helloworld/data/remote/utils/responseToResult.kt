package com.helloworld.data.remote.utils

import com.helloworld.common.utils.NetworkError
import com.helloworld.common.utils.ResultState
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): ResultState<T, NetworkError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                ResultState.Success(response.body<T>())
            } catch(e: NoTransformationFoundException) {
                ResultState.Error(NetworkError.SERIALIZATION)
            }
        }
        408 -> ResultState.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> ResultState.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> ResultState.Error(NetworkError.SERVER_ERROR)
        else -> ResultState.Error(NetworkError.UNKNOWN)
    }
}