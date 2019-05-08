package com.nikitamaslov.domain.interactor.application

import com.nikitamaslov.domain.defaults.Defaults
import com.nikitamaslov.domain.interactor.preferences.IsTemperatureUnitEmptyUseCase
import com.nikitamaslov.domain.repository.PreferencesRepository

interface InitializeApplicationUseCase {

    suspend operator fun invoke()
}

internal class InitializeApplicationUseCaseImpl(
    private val preferencesRepository: PreferencesRepository,
    private val isTemperatureUnitEmpty: IsTemperatureUnitEmptyUseCase
) : InitializeApplicationUseCase {

    private var isInProgress: Boolean = false

    override suspend fun invoke() {
        if (isInProgress) return

        isInProgress = true
        if (isTemperatureUnitEmpty()) {
            prepopulateTemperatureUnit()
        }
        isInProgress = false
    }

    private suspend fun prepopulateTemperatureUnit() {
        preferencesRepository.setTemperatureUnit(Defaults.temperatureUnit)
    }
}