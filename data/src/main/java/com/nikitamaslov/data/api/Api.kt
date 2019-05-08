package com.nikitamaslov.data.api

import com.nikitamaslov.data.model.DurationObject
import com.nikitamaslov.data.model.ForecastObject
import com.nikitamaslov.data.model.LocationObject
import com.nikitamaslov.data.model.TemperatureObject

interface Api {

    suspend fun getUpdateInterval(): DurationObject

    suspend fun fetchForecast(
        location: LocationObject,
        temperatureUnit: TemperatureObject.Unit
    ): ForecastObject
}