package com.gritacademy.se

// Converts JSON data into Kotlin objects
data class WeatherResponse(
    val main: TemperatureData,
    val wind: WeatherWind
)
data class TemperatureData(
    val temp: Double,
    val humidity: Int
)
data class WeatherWind(
    val speed: Double
)