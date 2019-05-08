package com.nikitamaslov.data.repository

import com.nikitamaslov.data.api.Api
import com.nikitamaslov.data.exception.ApiException
import com.nikitamaslov.data.mapper.*
import com.nikitamaslov.data.model.LocationObject
import com.nikitamaslov.data.model.TemperatureObject
import com.nikitamaslov.data.storage.Storage
import com.nikitamaslov.domain.model.Duration
import com.nikitamaslov.domain.model.Forecast
import com.nikitamaslov.domain.model.Location
import com.nikitamaslov.domain.model.Temperature
import com.nikitamaslov.domain.repository.ForecastRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.map
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

internal class ForecastRepositoryImpl(
    private val api: Api,
    private val storage: Storage
) : ForecastRepository {

    private var fetchJob: Job? = null

    override suspend fun updateForecast(
        location: Location,
        temperatureUnit: Temperature.Unit
    ) = coroutineScope {
        val loc = location.mapToLocationObject()
        val tempUnit = temperatureUnit.mapToTemperatureUnitObject()

        fetchJob?.cancel()
        fetchJob = launchFetchAndSave(loc, tempUnit)
        fetchJob!!.join()
    }

    private fun CoroutineScope.launchFetchAndSave(
        location: LocationObject,
        temperatureUnit: TemperatureObject.Unit
    ): Job = launch {

        val forecast = try {
            api.fetchForecast(location, temperatureUnit)
        } catch (e: ApiException) {
            throw e.mapToForecastFetchException()
        }

        storage.insertOrReplaceForecast(forecast)
    }

    override suspend fun receiveForecast(): ReceiveChannel<Forecast?> =
        storage.receiveForecast().map { it?.mapToForecast() }

    override suspend fun resetForecast() {
        storage.resetForecast()
    }

    override suspend fun getApiUpdateInterval(): Duration =
        api.getUpdateInterval().mapToDuration()
}