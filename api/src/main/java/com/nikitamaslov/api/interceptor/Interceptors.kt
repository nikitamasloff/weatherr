package com.nikitamaslov.api.interceptor

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

internal fun getAuthInterceptor(apiKey: String): Interceptor = Interceptor { chain ->

    val newUrl = chain.request().url()
        .newBuilder()
        .addQueryParameter("appid", apiKey)
        .build()

    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()

    chain.proceed(newRequest)
}

internal fun getLoggingInterceptor(): Interceptor = HttpLoggingInterceptor()