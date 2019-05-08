package com.nikitamaslov.domain.interactor.preferences

import com.nikitamaslov.domain.exception.notPrepopulatedError
import com.nikitamaslov.domain.model.Temperature
import com.nikitamaslov.domain.repository.PreferencesRepository
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.map

interface ReceiveTemperatureUnitUseCase {

    suspend operator fun invoke(): ReceiveChannel<Temperature.Unit>
}

internal class ReceiveTemperatureUnitUseCaseImpl(
    private val preferencesRepository: PreferencesRepository
) : ReceiveTemperatureUnitUseCase {

    override suspend fun invoke(): ReceiveChannel<Temperature.Unit> =
        preferencesRepository.receiveTemperatureUnit()
            .map { it ?: notPrepopulatedError(subject = Temperature.Unit::class) }
}