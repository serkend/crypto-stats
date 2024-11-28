package com.helloworld.features.coinList.model

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.helloworld.domain.model.Coin
import com.helloworld.features.coinList.utils.getDrawableIdForCoin
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd.toDisplayable(),
        marketCapUsd = marketCapUsd.toDisplayable(),
        changePercent24Hr = changePercent24Hr.toDisplayable(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Double.toDisplayable(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(value = this, formatted = formatter.format(this))
}