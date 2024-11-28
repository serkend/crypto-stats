package com.helloworld.features.coinList

import androidx.compose.runtime.Immutable
import com.helloworld.features.coinList.model.CoinUi

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null
)