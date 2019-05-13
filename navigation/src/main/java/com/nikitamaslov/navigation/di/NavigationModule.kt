package com.nikitamaslov.navigation.di

import com.nikitamaslov.navigation.Navigator
import com.nikitamaslov.navigation.NavigatorImpl
import org.koin.dsl.module

val navigationModule = module {

    factory<Navigator> { NavigatorImpl() }
}