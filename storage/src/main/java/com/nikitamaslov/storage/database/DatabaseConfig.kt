package com.nikitamaslov.storage.database

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.annotations.RealmModule

internal object DatabaseConfig {

    private const val REALM_FILE_NAME = "storage.database.realm"

    @RealmModule(library = true, allClasses = true)
    private class DatabaseModule

    fun createDefaultRealmConfiguration(): RealmConfiguration =
        RealmConfiguration.Builder()
            .modules(DatabaseModule())
            .name(REALM_FILE_NAME)
            .build()

    fun init(applicationContext: Context) {
        Realm.init(applicationContext)
    }
}