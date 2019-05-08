package com.nikitamaslov.domain.interactor.forecast

import com.nikitamaslov.domain.model.Location

internal interface GetLatestForecastLocationUseCase {

    suspend operator fun invoke(): Location?
}

internal class GetLatestForecastLocationUseCaseImpl(
    private val receiveForecast: ReceiveForecastUseCase
) : GetLatestForecastLocationUseCase {

    override suspend fun invoke(): Location? {
        val latestForecast = receiveForecast().receive()
        return latestForecast?.metadata?.location
    }
}