package com.helloworld.data.remote

import com.helloworld.data.remote.dto.CoinsResponseDto
import retrofit2.http.GET

interface CryptoApi {
    @GET("assets")
    suspend fun getCoins(): CoinsResponseDto
}