package com.nikitamaslov.weatherr

import android.app.Application
import com.nikitamaslov.weatherr.di.AppInjector
import com.nikitamaslov.weatherr.init.AppInitializer
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInitializer.init(this)
        AppInjector.inject(this)
    }
}