package com.nikitamaslov.domain.interactor.location

import com.nikitamaslov.domain.model.Location

internal interface GetCurrentLocationUseCase {

    suspend operator fun invoke(): Location?
}

internal class GetCurrentLocationUseCaseImpl(private val receiveLocation: ReceiveLocationUseCase) :
    GetCurrentLocationUseCase {

    override suspend fun invoke(): Location? = receiveLocation().receive()
}