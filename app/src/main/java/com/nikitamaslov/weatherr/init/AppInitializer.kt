package com.nikitamaslov.weatherr.init

import com.nikitamaslov.storage.initializer.StorageInitializer
import com.nikitamaslov.weatherr.App
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
internal object AppInitializer {

    fun init(app: App) {
        StorageInitializer.init(app)
    }
}