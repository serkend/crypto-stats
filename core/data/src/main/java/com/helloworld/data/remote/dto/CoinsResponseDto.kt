package com.helloworld.data.remote.dto

import com.helloworld.data.remote.dto.CoinDto
import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(
    val data: List<CoinDto>
)
