package com.nikitamaslov.domain.interactor.preferences

import com.nikitamaslov.domain.repository.PreferencesRepository

internal interface IsTemperatureUnitEmptyUseCase {

    suspend operator fun invoke(): Boolean
}

internal class IsTemperatureUnitEmptyUseCaseImpl(
    private val preferencesRepository: PreferencesRepository
) : IsTemperatureUnitEmptyUseCase {

    override suspend fun invoke(): Boolean =
        preferencesRepository.receiveTemperatureUnit().receive() == null
}