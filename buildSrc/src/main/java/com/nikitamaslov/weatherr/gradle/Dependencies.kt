package com.nikitamaslov.weatherr.gradle

private object Versions {

    val kotlin = "1.3.30"
}

object Libs {

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    object Android {

        val appCompat = "androidx.appcompat:appcompat:1.1.0-alpha04"
    }

    object Coroutines {
        private val version = "1.2.1"
        val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object Koin {
        private val version = "2.0.0-GA6"
        val core = "org.koin:koin-core:$version"
        val android = "org.koin:koin-android:$version"
        val androidScope = "org.koin:koin-androidx-scope:$version"
        val androidViewModel = "org.koin:koin-androidx-viewmodel:$version"
    }
}

object Plugins {

    val androidApplication = "com.android.application"
    val androidLibrary = "com.android.library"
    val kotlin = "kotlin"

    val kotlinAndroid = "kotlin-android"
    val kotlinAndroidExtensions = "kotlin-android-extensions"
    val kotlinKapt = "kotlin-kapt"

    val androidGradle = "com.android.tools.build:gradle:3.4.0"
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}
