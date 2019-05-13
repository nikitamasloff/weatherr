package com.nikitamaslov.presentation.di

import com.nikitamaslov.presentation.viewmodel.ForecastViewModel
import com.nikitamaslov.presentation.viewmodel.LocationViewModel
import com.nikitamaslov.presentation.viewmodel.PreferencesViewModel
import com.nikitamaslov.presentation.viewmodel.SplashViewModel
import kotlinx.coroutines.MainScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        ForecastViewModel(
            coroutineScope = MainScope(),
            navigator = get(),
            receiveForecastUseCase = get(),
            updateForecastUseCase = get()
        )
    }

    viewModel {
        LocationViewModel(
            coroutineScope = MainScope(),
            navigator = get(),
            receiveLocationUseCase = get(),
            setLocationUseCase = get()
        )
    }

    viewModel {
        PreferencesViewModel(
            coroutineScope = MainScope(),
            navigator = get(),
            receiveTemperatureUnitUseCase = get(),
            setTemperatureUnitUseCase = get()
        )
    }

    viewModel {
        SplashViewModel(
            coroutineScope = MainScope(),
            navigator = get(),
            initializeApplicationUseCase = get()
        )
    }
}