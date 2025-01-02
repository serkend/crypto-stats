package com.helloworld.features.coinList.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.helloworld.features.coinList.CoinListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CoinListViewModel::class)
    abstract fun bindLoginViewModel(viewModel: CoinListViewModel): ViewModel
    
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}