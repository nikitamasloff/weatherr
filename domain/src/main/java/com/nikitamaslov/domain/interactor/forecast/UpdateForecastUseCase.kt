package com.nikitamaslov.domain.interactor.forecast

import com.nikitamaslov.domain.exception.ForecastFetchException
import com.nikitamaslov.domain.exception.locationNotSpecifiedError
import com.nikitamaslov.domain.interactor.location.GetCurrentLocationUseCase
import com.nikitamaslov.domain.interactor.preferences.GetCurrentTemperatureUnitUseCase
import com.nikitamaslov.domain.model.Location
import com.nikitamaslov.domain.model.Temperature
import com.nikitamaslov.domain.repository.ForecastRepository
import com.nikitamaslov.domain.util.datetime.compareTo
import com.nikitamaslov.domain.util.datetime.currentDateTime
import com.nikitamaslov.domain.util.datetime.minus
import kotlinx.coroutines.coroutineScope

interface UpdateForecastUseCase {

    suspend operator fun invoke()
}

internal class UpdateForecastUseCaseImpl(
    private val forecastRepository: ForecastRepository,
    private val getCurrentLocation: GetCurrentLocationUseCase,
    private val getCurrentTemperatureUnit: GetCurrentTemperatureUnitUseCase,
    private val getLatestForecastDateTime: GetLatestForecastDateTimeUseCase,
    private val getLatestForecastLocation: GetLatestForecastLocationUseCase,
    private val getLatestForecastTemperatureUnit: GetLatestForecastTemperatureUnitUseCase
) : UpdateForecastUseCase {

    private var isInProgress: Boolean = false

    override suspend fun invoke() = coroutineScope {
        if (isInProgress) return@coroutineScope

        isInProgress = true

        val location = getCurrentLocation() ?: locationNotSpecifiedError()
        val temperatureUnit = getCurrentTemperatureUnit()

        when {
            isObsolete() -> update(location, temperatureUnit)
            isLocationDiffer(location) || isTemperatureUnitDiffer(temperatureUnit) -> {
                reset()
                update(location, temperatureUnit)
            }
        }

        isInProgress = false
    }

    private suspend fun update(location: Location, temperatureUnit: Temperature.Unit) {
        try {
            forecastRepository.updateForecast(location, temperatureUnit)
        } catch (e: ForecastFetchException) {
            throw e
        }
    }

    private suspend fun reset() {
        forecastRepository.resetForecast()
    }

    private suspend fun isObsolete(): Boolean {
        val current = currentDateTime()
        val lastUpdate = getLatestForecastDateTime() ?: return true
        val updateInterval = forecastRepository.getApiUpdateInterval()

        val diff = current - lastUpdate
        return diff > updateInterval
    }

    private suspend fun isTemperatureUnitDiffer(
        temperatureUnit: Temperature.Unit
    ): Boolean =
        temperatureUnit != getLatestForecastTemperatureUnit()

    private suspend fun isLocationDiffer(location: Location): Boolean =
        location != getLatestForecastLocation()
}