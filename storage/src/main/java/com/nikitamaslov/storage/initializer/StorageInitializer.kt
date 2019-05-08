package com.nikitamaslov.storage.initializer

import android.content.Context
import com.nikitamaslov.storage.database.DatabaseConfig

object StorageInitializer {

    fun init(applicationContext: Context) {
        DatabaseConfig.init(applicationContext)
    }
}