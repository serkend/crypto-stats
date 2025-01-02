package com.helloworld.features.coinList.di

import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [CoinListModule::class])
interface CoinListComponent {

    fun inject()

    @Subcomponent.Factory
    interface Factory {
        fun create(): CoinListComponent
    }
}
