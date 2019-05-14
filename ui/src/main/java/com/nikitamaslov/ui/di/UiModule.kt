package com.nikitamaslov.ui.di

import android.os.Handler
import org.koin.dsl.module

val uiModule = module {

    factory { Handler() }
}