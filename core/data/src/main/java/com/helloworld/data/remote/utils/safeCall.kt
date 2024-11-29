package com.helloworld.data.remote.utils

import com.google.gson.JsonParseException
import com.helloworld.common.utils.NetworkError
import com.helloworld.common.utils.ResultState
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.coroutineContext

//suspend inline fun <reified T> safeCall(
//    execute: () -> HttpResponse
//): ResultState<T, NetworkError> {
//    val response = try {
//        execute()
//    } catch(e: UnresolvedAddressException) {
//        return ResultState.Error(NetworkError.NO_INTERNET)
//    } catch(e: SerializationException) {
//        return ResultState.Error(NetworkError.SERIALIZATION)
//    } catch(e: Exception) {
//        coroutineContext.ensureActive()
//        return ResultState.Error(NetworkError.UNKNOWN)
//    }
//
//    return responseToResult(response)
//}

suspend fun <T> safeCall(
    apiCall: suspend () -> T
): ResultState<T, NetworkError> {
    return try {
        ResultState.Success(apiCall())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultState.Error(NetworkError.NO_INTERNET)
            is HttpException -> {
                when (throwable.code()) {
                    408 -> ResultState.Error(NetworkError.REQUEST_TIMEOUT)
                    429 -> ResultState.Error(NetworkError.TOO_MANY_REQUESTS)
                    in 500..599 -> ResultState.Error(NetworkError.SERVER_ERROR)
                    else -> ResultState.Error(NetworkError.UNKNOWN)
                }
            }
            is JsonParseException -> ResultState.Error(NetworkError.SERIALIZATION)
            else -> ResultState.Error(NetworkError.UNKNOWN)
        }
    }
}