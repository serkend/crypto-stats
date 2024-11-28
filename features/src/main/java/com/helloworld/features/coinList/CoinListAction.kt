package com.helloworld.features.coinList

import com.helloworld.features.coinList.model.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
}