package com.helloworld.domain.di

import com.helloworld.domain.repository.CryptoRepository
import com.helloworld.domain.usecases.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetCoinsUseCase(cryptoRepository: CryptoRepository) = GetCoinsUseCase(cryptoRepository)

}