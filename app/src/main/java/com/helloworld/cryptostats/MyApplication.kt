package com.helloworld.cryptostats

import android.app.Application

class MyApplication: Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
    }
}