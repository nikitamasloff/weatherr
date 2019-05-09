package com.nikitamaslov.data.di

import com.nikitamaslov.data.repository.ForecastRepositoryImpl
import com.nikitamaslov.data.repository.LocationRepositoryImpl
import com.nikitamaslov.data.repository.PreferencesRepositoryImpl
import com.nikitamaslov.domain.repository.ForecastRepository
import com.nikitamaslov.domain.repository.LocationRepository
import com.nikitamaslov.domain.repository.PreferencesRepository
import org.koin.dsl.module

val dataModule = module {

    single<ForecastRepository> {
        ForecastRepositoryImpl(
            api = get(),
            storage = get()
        )
    }

    single<LocationRepository> {
        LocationRepositoryImpl(storage = get())
    }

    single<PreferencesRepository> {
        PreferencesRepositoryImpl(storage = get())
    }
}