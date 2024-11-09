package com.helloworld.domain.usecases

import com.helloworld.common.utils.NetworkError
import com.helloworld.common.utils.ResultState
import com.helloworld.domain.model.Coin
import com.helloworld.domain.repository.CryptoRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CryptoRepository) {
    suspend operator fun invoke(): ResultState<List<Coin>, NetworkError> {
        return repository.getCoins()
    }
}