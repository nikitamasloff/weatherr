package com.nikitamaslov.delegated.di

import com.nikitamaslov.api.di.apiKeyQualifier
import com.nikitamaslov.api.util.network.NetworkManager
import com.nikitamaslov.delegated.BuildConfig
import com.nikitamaslov.delegated.network.NetworkManagerImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val delegatedModule = module {

    factory(apiKeyQualifier) { BuildConfig.OPENWEATHERMAP_API_KEY }

    factory<NetworkManager> { NetworkManagerImpl(applicationContext = androidApplication()) }
}