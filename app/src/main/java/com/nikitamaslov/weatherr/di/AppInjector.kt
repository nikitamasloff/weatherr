package com.nikitamaslov.weatherr.di

import com.nikitamaslov.api.di.apiModule
import com.nikitamaslov.data.di.dataModule
import com.nikitamaslov.delegated.di.delegatedModule
import com.nikitamaslov.domain.di.domainModule
import com.nikitamaslov.navigation.di.navigationModule
import com.nikitamaslov.presentation.di.presentationModule
import com.nikitamaslov.storage.di.storageModule
import com.nikitamaslov.ui.di.uiModule
import com.nikitamaslov.weatherr.App
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
internal object AppInjector {

    fun inject(app: App) {
        startKoin {
            androidLogger()
            androidContext(app)
            modules(modules)
        }
    }

    private val modules = listOf(
        apiModule,
        dataModule,
        delegatedModule,
        domainModule,
        navigationModule,
        presentationModule,
        storageModule,
        uiModule
    )
}