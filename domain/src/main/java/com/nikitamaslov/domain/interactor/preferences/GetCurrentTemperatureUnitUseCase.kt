package com.nikitamaslov.domain.interactor.preferences

import com.nikitamaslov.domain.model.Temperature

internal interface GetCurrentTemperatureUnitUseCase {

    suspend operator fun invoke(): Temperature.Unit
}

internal class GetCurrentTemperatureUnitUseCaseImpl(
    private val receiveTemperatureUnit: ReceiveTemperatureUnitUseCase
) : GetCurrentTemperatureUnitUseCase {

    override suspend fun invoke(): Temperature.Unit = receiveTemperatureUnit().receive()
}