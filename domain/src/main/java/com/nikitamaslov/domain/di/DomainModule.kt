package com.nikitamaslov.domain.di

import com.nikitamaslov.domain.interactor.application.InitializeApplicationUseCase
import com.nikitamaslov.domain.interactor.application.InitializeApplicationUseCaseImpl
import com.nikitamaslov.domain.interactor.forecast.*
import com.nikitamaslov.domain.interactor.location.*
import com.nikitamaslov.domain.interactor.preferences.*
import org.koin.dsl.module

val domainModule = module {

    factory<InitializeApplicationUseCase> {
        InitializeApplicationUseCaseImpl(
            preferencesRepository = get(),
            isTemperatureUnitEmpty = get()
        )
    }

    factory<GetLatestForecastDateTimeUseCase> {
        GetLatestForecastDateTimeUseCaseImpl(receiveForecast = get())
    }

    factory<GetLatestForecastLocationUseCase> {
        GetLatestForecastLocationUseCaseImpl(receiveForecast = get())
    }

    factory<GetLatestForecastTemperatureUnitUseCase> {
        GetLatestForecastTemperatureUnitUseCaseImpl(receiveForecast = get())
    }

    factory<GetLatestForecastUseCase> {
        GetLatestForecastUseCaseImpl(receiveForecast = get())
    }

    factory<ReceiveForecastUseCase> {
        ReceiveForecastUseCaseImpl(forecastRepository = get())
    }

    factory<UpdateForecastUseCase> {
        UpdateForecastUseCaseImpl(
            forecastRepository = get(),
            getCurrentLocation = get(),
            getCurrentTemperatureUnit = get(),
            getLatestForecastDateTime = get(),
            getLatestForecastLocation = get(),
            getLatestForecastTemperatureUnit = get()
        )
    }

    factory<GetCurrentLocationUseCase> {
        GetCurrentLocationUseCaseImpl(receiveLocation = get())
    }

    factory<ReceiveLocationUseCase> {
        ReceiveLocationUseCaseImpl(locationRepository = get())
    }

    factory<SetLocationUseCase> {
        SetLocationUseCaseImpl(
            locationRepository = get(),
            getCurrentLocation = get()
        )
    }

    factory<GetCurrentTemperatureUnitUseCase> {
        GetCurrentTemperatureUnitUseCaseImpl(receiveTemperatureUnit = get())
    }

    factory<IsTemperatureUnitEmptyUseCase> {
        IsTemperatureUnitEmptyUseCaseImpl(preferencesRepository = get())
    }

    factory<ReceiveTemperatureUnitUseCase> {
        ReceiveTemperatureUnitUseCaseImpl(preferencesRepository = get())
    }

    factory<SetTemperatureUnitUseCase> {
        SetTemperatureUnitUseCaseImpl(
            preferencesRepository = get(),
            getCurrentTemperatureUnit = get()
        )
    }
}