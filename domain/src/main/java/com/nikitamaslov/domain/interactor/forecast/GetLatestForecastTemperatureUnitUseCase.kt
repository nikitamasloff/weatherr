package com.nikitamaslov.domain.interactor.forecast

import com.nikitamaslov.domain.model.Temperature

internal interface GetLatestForecastTemperatureUnitUseCase {

    suspend operator fun invoke(): Temperature.Unit?
}

internal class GetLatestForecastTemperatureUnitUseCaseImpl(
    private val receiveForecast: ReceiveForecastUseCase
) : GetLatestForecastTemperatureUnitUseCase {

    override suspend fun invoke(): Temperature.Unit? {
        val latestForecast = receiveForecast().receive()
        return latestForecast?.metadata?.temperatureUnit
    }
}