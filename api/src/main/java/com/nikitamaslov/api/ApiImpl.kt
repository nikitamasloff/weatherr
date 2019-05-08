package com.nikitamaslov.api

import com.nikitamaslov.api.mapper.mapToDurationObject
import com.nikitamaslov.api.mapper.mapToForecastObject
import com.nikitamaslov.api.mapper.mapToLocationModel
import com.nikitamaslov.api.mapper.mapToTemperatureUnitModel
import com.nikitamaslov.api.model.ForecastModel
import com.nikitamaslov.api.model.LocationModel
import com.nikitamaslov.api.model.TemperatureModel
import com.nikitamaslov.api.model.WeatherModel
import com.nikitamaslov.api.parser.ParseException
import com.nikitamaslov.api.parser.Parser
import com.nikitamaslov.api.service.OpenWeatherMapService
import com.nikitamaslov.api.service.OpenWeatherMapServiceAdapter
import com.nikitamaslov.api.util.datetime.currentDateTime
import com.nikitamaslov.api.util.network.NetworkManager
import com.nikitamaslov.data.api.Api
import com.nikitamaslov.data.exception.NetworkConnectionException
import com.nikitamaslov.data.exception.ServerException
import com.nikitamaslov.data.model.DurationObject
import com.nikitamaslov.data.model.ForecastObject
import com.nikitamaslov.data.model.LocationObject
import com.nikitamaslov.data.model.TemperatureObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

internal class ApiImpl(
    private val service: OpenWeatherMapServiceAdapter,
    private val parser: Parser,
    private val networkManager: NetworkManager,
    private val ioDispatcher: CoroutineDispatcher
) : Api {

    override suspend fun getUpdateInterval(): DurationObject =
        OpenWeatherMapService.updateInterval.mapToDurationObject()

    override suspend fun fetchForecast(
        location: LocationObject,
        temperatureUnit: TemperatureObject.Unit
    ): ForecastObject = try {

        val forecast = fetchForecast(
            location = location.mapToLocationModel(),
            temperatureUnit = temperatureUnit.mapToTemperatureUnitModel()
        )
        forecast.mapToForecastObject()

    } catch (e: ParseException) {
        throw ServerException()
    } catch (e: Exception) {
        throw when {
            !networkManager.isConnected -> NetworkConnectionException()
            else -> ServerException()
        }
    }

    private suspend fun fetchForecast(
        location: LocationModel,
        temperatureUnit: TemperatureModel.Unit
    ) = coroutineScope {

        parser.setTemperatureUnit(temperatureUnit)

        val currentWeather: WeatherModel = withContext(ioDispatcher) {
            service
                .fetchCurrentWeatherAsync(location.coordinates, temperatureUnit)
                .await()
                .let(parser::parse)
        }

        val futureWeather: Set<WeatherModel> = withContext(ioDispatcher) {
            service
                .fetchHourlyForecastAsync(location.coordinates, temperatureUnit)
                .await()
                .let(parser::parse)
        }

        val metadata = ForecastModel.Metadata(
            location = location,
            temperatureUnit = temperatureUnit,
            dateTime = currentDateTime()
        )

        ForecastModel(currentWeather, futureWeather, metadata)
    }
}