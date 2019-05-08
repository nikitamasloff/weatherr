package com.nikitamaslov.domain.interactor.forecast

import com.nikitamaslov.domain.model.Forecast
import com.nikitamaslov.domain.repository.ForecastRepository
import kotlinx.coroutines.channels.ReceiveChannel

interface ReceiveForecastUseCase {

    suspend operator fun invoke(): ReceiveChannel<Forecast?>
}

internal class ReceiveForecastUseCaseImpl(private val forecastRepository: ForecastRepository) :
    ReceiveForecastUseCase {

    override suspend fun invoke(): ReceiveChannel<Forecast?> = forecastRepository.receiveForecast()
}