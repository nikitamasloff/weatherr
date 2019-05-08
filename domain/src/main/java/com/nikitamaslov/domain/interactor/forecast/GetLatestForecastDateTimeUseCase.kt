package com.nikitamaslov.domain.interactor.forecast

import com.nikitamaslov.domain.model.DateTime

internal interface GetLatestForecastDateTimeUseCase {

    suspend operator fun invoke(): DateTime?
}

internal class GetLatestForecastDateTimeUseCaseImpl(
    private val receiveForecast: ReceiveForecastUseCase
) : GetLatestForecastDateTimeUseCase {

    override suspend fun invoke(): DateTime? {
        val latestForecast = receiveForecast().receive()
        return latestForecast?.metadata?.dateTime
    }
}