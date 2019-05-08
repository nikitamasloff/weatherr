package com.nikitamaslov.data.repository

import com.nikitamaslov.data.mapper.mapToTemperatureUnit
import com.nikitamaslov.data.mapper.mapToTemperatureUnitObject
import com.nikitamaslov.data.storage.Storage
import com.nikitamaslov.domain.model.Temperature
import com.nikitamaslov.domain.repository.PreferencesRepository
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.map

internal class PreferencesRepositoryImpl(private val storage: Storage) : PreferencesRepository {

    override suspend fun receiveTemperatureUnit(): ReceiveChannel<Temperature.Unit?> =
        storage.receiveTemperatureUnit().map { it?.mapToTemperatureUnit() }

    override suspend fun setTemperatureUnit(temperatureUnit: Temperature.Unit) {
        storage.insertOrReplaceTemperatureUnit(temperatureUnit.mapToTemperatureUnitObject())
    }
}