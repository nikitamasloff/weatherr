package com.nikitamaslov.api.service

import com.nikitamaslov.api.model.DurationModel
import com.nikitamaslov.api.model.WeatherModel
import com.nikitamaslov.api.service.response.CurrentWeatherResponse
import com.nikitamaslov.api.service.response.HourlyForecastResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

internal interface OpenWeatherMapService {

    @GET("forecast")
    fun fetchHourlyForecastAsync(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit: String
    ): Deferred<HourlyForecastResponse>

    @GET("weather")
    fun fetchCurrentWeatherAsync(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit: String
    ): Deferred<CurrentWeatherResponse>

    companion object {

        val updateInterval = DurationModel(/* 2 hours */2 * 3_600_000)

        const val CELSIUS_UNIT = "metric"
        const val FAHRENHEIT_UNIT = "imperial"
    }
}