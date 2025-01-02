package com.helloworld.features.coinList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.helloworld.features.R
import com.helloworld.features.coinList.model.CoinUi
import com.helloworld.features.coinList.model.toDisplayable
import com.helloworld.uikit.ui.theme.CryptoStatsTheme

@Composable
fun CoinListItem(
    coinUi: CoinUi,
    modifier: Modifier,
    onClick: (CoinUi) -> Unit
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    Row (
        modifier = modifier.padding(16.dp).clickable { onClick(coinUi) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                imageVector = ImageVector.vectorResource(id = coinUi.iconRes),
                contentDescription = coinUi.name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(85.dp)
            )
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = coinUi.symbol,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor
                )
                Text(
                    text = coinUi.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    color = contentColor
                )
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$ ${coinUi.priceUsd.formatted}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            PriceChange(
                change = coinUi.changePercent24Hr
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CoinListItemPreview() {
    CryptoStatsTheme {
        CoinListItem(
            coinUi = CoinUi(
                id = "noluisse",
                rank = 6974,
                name = "Cassandra Battle",
                symbol = "eum",
                marketCapUsd = 6.7.toDisplayable(),
                priceUsd = 8.9.toDisplayable(),
                changePercent24Hr = 10.11.toDisplayable(),
                iconRes = com.helloworld.uikit.R.drawable.btc
            ),
            modifier = Modifier,
            onClick = {}
        )
    }
}