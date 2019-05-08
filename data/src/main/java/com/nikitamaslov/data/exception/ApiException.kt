package com.nikitamaslov.data.exception

sealed class ApiException(message: String? = null) : Exception(message)

class NetworkConnectionException(message: String? = null) : ApiException(message)

class ServerException(message: String? = null) : ApiException(message)