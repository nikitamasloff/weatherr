package com.nikitamaslov.presentation.model

data class ForecastFigure(
    val currentWeather: WeatherFigure,
    val futureWeather: Set<WeatherFigure>
)