package com.nikitamaslov.domain.repository

import com.nikitamaslov.domain.model.Duration
import com.nikitamaslov.domain.model.Forecast
import com.nikitamaslov.domain.model.Location
import com.nikitamaslov.domain.model.Temperature
import kotlinx.coroutines.channels.ReceiveChannel

interface ForecastRepository {

    suspend fun receiveForecast(): ReceiveChannel<Forecast?>

    suspend fun updateForecast(
        location: Location,
        temperatureUnit: Temperature.Unit
    )

    suspend fun resetForecast()

    suspend fun getApiUpdateInterval(): Duration
}