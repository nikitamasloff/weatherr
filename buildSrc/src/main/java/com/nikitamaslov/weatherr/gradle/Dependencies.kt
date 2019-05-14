package com.nikitamaslov.weatherr.gradle

private object Versions {

    val kotlin = "1.3.30"
}

object Libs {

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    object Android {

        val appCompat = "androidx.appcompat:appcompat:1.1.0-alpha04"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    }

    object Lifecycle {
        private const val version = "2.1.0-alpha04"
        val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
        val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:$version"
    }

    object Navigation {
        private const val version = "2.1.0-alpha02"
        val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        val ui = "androidx.navigation:navigation-ui-ktx:$version"
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

    object Retrofit {
        private val version = "2.5.0"
        val core = "com.squareup.retrofit2:retrofit:$version"
        val moshiConverterFactory = "com.squareup.retrofit2:converter-moshi:$version"
        val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.13.1"
        val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    }

    object MaterialDialog {
        private val version = "3.0.0-alpha1"
        val core = "com.afollestad.material-dialogs:core:$version"
        val lifecycle = "com.afollestad.material-dialogs:lifecycle:$version"
    }

    object MaterialPreferences {
        const val core = "com.yarolegovich:mp:1.0.9"
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

    val realmGradle = "io.realm:realm-gradle-plugin:5.10.0"
    val realmAndroid = "realm-android"

    val navigationSafeArgsGradle =
        "androidx.navigation:navigation-safe-args-gradle-plugin:2.1.0-alpha02"
    val navigationSafeArgs = "androidx.navigation.safeargs.kotlin"
}
