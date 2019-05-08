package com.nikitamaslov.data.storage

import com.nikitamaslov.data.model.ForecastObject
import com.nikitamaslov.data.model.LocationObject
import com.nikitamaslov.data.model.TemperatureObject
import kotlinx.coroutines.channels.ReceiveChannel

interface Storage {

    suspend fun receiveTemperatureUnit(): ReceiveChannel<TemperatureObject.Unit?>

    suspend fun insertOrReplaceTemperatureUnit(temperatureUnit: TemperatureObject.Unit)

    suspend fun receiveLocation(): ReceiveChannel<LocationObject?>

    suspend fun insertOrReplaceLocation(location: LocationObject)

    suspend fun receiveForecast(): ReceiveChannel<ForecastObject?>

    suspend fun insertOrReplaceForecast(forecast: ForecastObject)

    suspend fun resetForecast()
}