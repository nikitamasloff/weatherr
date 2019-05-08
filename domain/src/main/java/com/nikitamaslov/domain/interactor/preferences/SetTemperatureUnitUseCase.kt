package com.nikitamaslov.domain.interactor.preferences

import com.nikitamaslov.domain.model.Temperature
import com.nikitamaslov.domain.repository.PreferencesRepository

interface SetTemperatureUnitUseCase {

    suspend operator fun invoke(temperatureUnit: Temperature.Unit)
}

internal class SetTemperatureUnitUseCaseImpl(
    private val preferencesRepository: PreferencesRepository,
    private val getCurrentTemperatureUnit: GetCurrentTemperatureUnitUseCase
) : SetTemperatureUnitUseCase {

    override suspend fun invoke(temperatureUnit: Temperature.Unit) {
        if (getCurrentTemperatureUnit() != temperatureUnit) {
            setTemperatureUnit(temperatureUnit)
        }
    }

    private suspend fun setTemperatureUnit(temperatureUnit: Temperature.Unit) {
        preferencesRepository.setTemperatureUnit(temperatureUnit)
    }
}