package com.nikitamaslov.domain.interactor.forecast

import com.nikitamaslov.domain.model.Forecast

internal interface GetLatestForecastUseCase {

    suspend operator fun invoke(): Forecast?
}

internal class GetLatestForecastUseCaseImpl(private val receiveForecast: ReceiveForecastUseCase) :
    GetLatestForecastUseCase {

    override suspend fun invoke(): Forecast? = receiveForecast().receive()
}