package com.nikitamaslov.domain.repository

import com.nikitamaslov.domain.model.Location
import kotlinx.coroutines.channels.ReceiveChannel

interface LocationRepository {

    suspend fun setLocation(location: Location)

    suspend fun receiveLocation(): ReceiveChannel<Location?>
}