package com.nikitamaslov.domain.interactor.location

import com.nikitamaslov.domain.model.Location
import com.nikitamaslov.domain.repository.LocationRepository
import kotlinx.coroutines.channels.ReceiveChannel

interface ReceiveLocationUseCase {

    suspend operator fun invoke(): ReceiveChannel<Location?>
}

internal class ReceiveLocationUseCaseImpl(private val locationRepository: LocationRepository) :
    ReceiveLocationUseCase {

    override suspend fun invoke(): ReceiveChannel<Location?> = locationRepository.receiveLocation()
}