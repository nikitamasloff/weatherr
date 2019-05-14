package com.nikitamaslov.ui.model

import androidx.annotation.DrawableRes
import com.nikitamaslov.ui.R

internal sealed class WeatherForm(
    val type: Type,
    val temperature: TemperatureForm,
    val descriptor: Descriptor
) {

    data class Descriptor(val description: String)
    enum class Type(
        @DrawableRes val smallDrawableId: Int,
        @DrawableRes val largeDrawableId: Int
    ) {
        CLEAR(
            smallDrawableId = R.drawable.ic_clear_black_40dp,
            largeDrawableId = R.drawable.ic_clear_black_72dp
        ),
        CLOUDS(
            smallDrawableId = R.drawable.ic_clouds_black_40dp,
            largeDrawableId = R.drawable.ic_clouds_black_72dp
        ),
        RAIN(
            smallDrawableId = R.drawable.ic_rain_black_40dp,
            largeDrawableId = R.drawable.ic_rain_black_72dp
        ),
        SNOW(
            smallDrawableId = R.drawable.ic_snow_black_40dp,
            largeDrawableId = R.drawable.ic_snow_black_72dp
        ),
        DRIZZLE(
            smallDrawableId = R.drawable.ic_drizzle_black_40dp,
            largeDrawableId = R.drawable.ic_drizzle_black_72dp
        ),
        THUNDERSTORM(
            smallDrawableId = R.drawable.ic_thunderstorm_black_40dp,
            largeDrawableId = R.drawable.ic_thunderstorm_black_72dp
        ),
        ATMOSPHERE(
            smallDrawableId = R.drawable.ic_atmosphere_black_40dp,
            largeDrawableId = R.drawable.ic_atmosphere_black_72dp
        )
    }
}

internal class CurrentWeatherForm(
    type: Type,
    temperature: TemperatureForm,
    descriptor: Descriptor
) : WeatherForm(type, temperature, descriptor)

internal class FutureWeatherForm(
    val dateTime: DateTimeForm,
    type: Type,
    temperature: TemperatureForm,
    descriptor: Descriptor
) : WeatherForm(type, temperature, descriptor)