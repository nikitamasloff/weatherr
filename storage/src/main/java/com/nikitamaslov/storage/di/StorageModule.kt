package com.nikitamaslov.storage.di

import com.nikitamaslov.data.storage.Storage
import com.nikitamaslov.storage.StorageImpl
import com.nikitamaslov.storage.database.Database
import com.nikitamaslov.storage.database.DatabaseConfig
import com.nikitamaslov.storage.database.RealmProvider
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.dsl.module

val storageModule = module {

    factory<RealmConfiguration> { DatabaseConfig.createDefaultRealmConfiguration() }

    factory<RealmProvider> {
        {
            val realmConfiguration: RealmConfiguration = get()
            Realm.getInstance(realmConfiguration)
        }
    }

    factory { Database(realmProvider = get()) }

    single<Storage> { StorageImpl(database = get()) }
}