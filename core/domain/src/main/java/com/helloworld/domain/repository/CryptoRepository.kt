package com.helloworld.domain.repository

import com.helloworld.common.utils.NetworkError
import com.helloworld.common.utils.ResultState
import com.helloworld.domain.model.Coin

interface CryptoRepository {
    suspend fun getCoins(): ResultState<List<Coin>, NetworkError>
}