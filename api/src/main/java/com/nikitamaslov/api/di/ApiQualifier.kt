package com.nikitamaslov.api.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named

val apiKeyQualifier: Qualifier = named("api_key")