package com.helloworld.features.coinList

import com.helloworld.common.utils.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}