package com.nikitamaslov.api.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nikitamaslov.api.ApiImpl
import com.nikitamaslov.api.endpoint.Endpoints
import com.nikitamaslov.api.interceptor.getAuthInterceptor
import com.nikitamaslov.api.interceptor.getLoggingInterceptor
import com.nikitamaslov.api.parser.Parser
import com.nikitamaslov.api.parser.ParserImpl
import com.nikitamaslov.api.service.OpenWeatherMapService
import com.nikitamaslov.api.service.OpenWeatherMapServiceAdapter
import com.nikitamaslov.api.service.OpenWeatherMapServiceAdapterImpl
import com.nikitamaslov.data.api.Api
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val apiModule = module {

    factory<OkHttpClient> {
        val apiKey: String = get(apiKeyQualifier)

        OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(getAuthInterceptor(apiKey))
            .build()
    }

    factory<Retrofit> {
        val client: OkHttpClient = get()

        Retrofit.Builder()
            .client(client)
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory<OpenWeatherMapService> {
        val retrofit: Retrofit = get()
        retrofit.create()
    }

    factory<OpenWeatherMapServiceAdapter> {
        OpenWeatherMapServiceAdapterImpl(openWeatherMapService = get())
    }

    factory<Parser> { ParserImpl() }

    single<Api> {
        ApiImpl(
            service = get(),
            parser = get(),
            networkManager = get(),
            ioDispatcher = Dispatchers.IO
        )
    }
}