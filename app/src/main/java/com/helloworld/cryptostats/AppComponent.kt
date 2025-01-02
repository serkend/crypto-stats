package com.helloworld.cryptostats

import android.app.Application
import com.helloworld.data.remote.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(application: Application)
    
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}