package com.nikitamaslov.domain.exception

sealed class ForecastFetchException(message: String? = null) : Exception(message) {

    companion object Factory {
        fun networkConnection(message: String? = null) = NetworkConnectionException(message)
        fun api(message: String? = null) = ApiException(message)
    }
}

class NetworkConnectionException(message: String? = null) : ForecastFetchException(message)

class ApiException(message: String? = null) : ForecastFetchException(message)