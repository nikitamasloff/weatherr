package com.nikitamaslov.domain.interactor.location

import com.nikitamaslov.domain.model.Location
import com.nikitamaslov.domain.repository.LocationRepository

interface SetLocationUseCase {

    suspend operator fun invoke(location: Location)
}

internal class SetLocationUseCaseImpl(
    private val locationRepository: LocationRepository,
    private val getCurrentLocation: GetCurrentLocationUseCase
) : SetLocationUseCase {

    override suspend fun invoke(location: Location) {
        if (getCurrentLocation() != location) {
            setLocation(location)
        }
    }

    private suspend fun setLocation(location: Location) {
        locationRepository.setLocation(location)
    }
}