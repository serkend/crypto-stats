package com.helloworld.data.remote.utils

import com.helloworld.common.utils.NetworkError
import com.helloworld.common.utils.ResultState
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): ResultState<T, NetworkError> {
    val response = try {
        execute()
    } catch(e: UnresolvedAddressException) {
        return ResultState.Error(NetworkError.NO_INTERNET)
    } catch(e: SerializationException) {
        return ResultState.Error(NetworkError.SERIALIZATION)
    } catch(e: Exception) {
        coroutineContext.ensureActive()
        return ResultState.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}