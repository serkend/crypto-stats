package com.helloworld.features.coinList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helloworld.common.utils.onFailure
import com.helloworld.common.utils.onSuccess
import com.helloworld.domain.usecases.GetCoinsUseCase
import com.helloworld.features.coinList.model.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinListViewModel @Inject constructor(val getCoinsUseCase: GetCoinsUseCase) : ViewModel() {

    private var _coinListStateFlow = MutableStateFlow(CoinListState())
    val coinListState = _coinListStateFlow
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private var _eventChannel = Channel<CoinListEvent>()
    val eventChannel = _eventChannel.receiveAsFlow()

    fun loadCoins() {
        viewModelScope.launch {
            getCoinsUseCase()
                .onSuccess { coins ->
                    _coinListStateFlow.update {
                        it.copy(isLoading = false, coins = coins.map { coin -> coin.toCoinUi() })
                    }
                }
                .onFailure { error ->
                    _coinListStateFlow.update { it.copy(isLoading = false) }
                    _eventChannel.send(CoinListEvent.Error(error))
                }
        }
    }

    fun onAction(coinListAction: CoinListAction) {}
}