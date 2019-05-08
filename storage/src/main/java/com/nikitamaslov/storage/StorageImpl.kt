package com.nikitamaslov.storage

import com.nikitamaslov.data.model.ForecastObject
import com.nikitamaslov.data.model.LocationObject
import com.nikitamaslov.data.model.TemperatureObject
import com.nikitamaslov.data.storage.Storage
import com.nikitamaslov.storage.database.Database
import com.nikitamaslov.storage.database.insertOrReplace
import com.nikitamaslov.storage.database.receive
import com.nikitamaslov.storage.mapper.*
import com.nikitamaslov.storage.model.single.SingleForecastEntity
import com.nikitamaslov.storage.model.single.SingleLocationEntity
import com.nikitamaslov.storage.model.single.SingleTemperatureUnitWrapperEntity
import kotlinx.coroutines.channels.ReceiveChannel

internal class StorageImpl(private val database: Database) : Storage {

    override suspend fun receiveTemperatureUnit(): ReceiveChannel<TemperatureObject.Unit?> {
        val mapper = SingleTemperatureUnitWrapperEntity::mapToTemperatureUnitObject
        return database.receive(mapper)
    }

    override suspend fun insertOrReplaceTemperatureUnit(temperatureUnit: TemperatureObject.Unit) {
        val mapper = TemperatureObject.Unit::mapToSingleTemperatureUnitWrapperEntity
        database.insertOrReplace(temperatureUnit, mapper)
    }

    override suspend fun receiveLocation(): ReceiveChannel<LocationObject?> {
        val mapper = SingleLocationEntity::mapToLocationObject
        return database.receive(mapper)
    }

    override suspend fun insertOrReplaceLocation(location: LocationObject) {
        val mapper = LocationObject::mapToSingleLocationEntity
        database.insertOrReplace(location, mapper)
    }

    override suspend fun receiveForecast(): ReceiveChannel<ForecastObject?> {
        val mapper = SingleForecastEntity::mapToForecastObject
        return database.receive(mapper)
    }

    override suspend fun insertOrReplaceForecast(forecast: ForecastObject) {
        val mapper = ForecastObject::mapToSingleForecastEntity
        database.insertOrReplace(forecast, mapper)
    }

    override suspend fun resetForecast() {
        database.delete<SingleForecastEntity>()
    }
}