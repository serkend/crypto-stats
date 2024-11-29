package com.helloworld.data.remote.repository

import com.helloworld.common.utils.NetworkError
import com.helloworld.common.utils.ResultState
import com.helloworld.common.utils.map
import com.helloworld.data.remote.CryptoApi
import com.helloworld.data.remote.utils.constructUrl
import com.helloworld.data.remote.utils.safeCall
import com.helloworld.domain.model.Coin
import com.helloworld.domain.repository.CryptoRepository
import com.helloworld.data.remote.dto.CoinsResponseDto
import com.helloworld.data.remote.mappers.toCoin
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(private val cryptoApi: CryptoApi) : CryptoRepository {
//    override suspend fun getCoins(): ResultState<List<Coin>, NetworkError> {
//        return safeCall<CoinsResponseDto> {
//            httpClient.get(
//                urlString = constructUrl("/assets")
//            )
//        }.map { response ->
//            response.data.map { it.toCoin() }
//        }
//    }

    override suspend fun getCoins(): ResultState<List<Coin>, NetworkError> {
        return safeCall {
            cryptoApi.getCoins()
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}