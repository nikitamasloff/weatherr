package com.nikitamaslov.data.repository

import com.nikitamaslov.data.mapper.mapToLocation
import com.nikitamaslov.data.mapper.mapToLocationObject
import com.nikitamaslov.data.storage.Storage
import com.nikitamaslov.domain.model.Location
import com.nikitamaslov.domain.repository.LocationRepository
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.map

internal class LocationRepositoryImpl(private val storage: Storage) : LocationRepository {

    override suspend fun receiveLocation(): ReceiveChannel<Location?> =
        storage.receiveLocation().map { it?.mapToLocation() }

    override suspend fun setLocation(location: Location) {
        storage.insertOrReplaceLocation(location.mapToLocationObject())
    }
}