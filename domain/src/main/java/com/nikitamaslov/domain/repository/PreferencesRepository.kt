package com.nikitamaslov.domain.repository

import com.nikitamaslov.domain.model.Temperature
import kotlinx.coroutines.channels.ReceiveChannel

interface PreferencesRepository {

    suspend fun setTemperatureUnit(temperatureUnit: Temperature.Unit)

    suspend fun receiveTemperatureUnit(): ReceiveChannel<Temperature.Unit?>
}