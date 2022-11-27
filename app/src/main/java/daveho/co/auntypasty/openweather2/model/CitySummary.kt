package daveho.co.auntypasty.openweather2.model

import java.time.Instant

data class CitySummary(
    val id: Int,
    val cityName: String,
    val country: String,
    val weatherTime: Int,
    val tempCelcius: Float,
    val windSpeedMps: Float,
    val windDirection: Float,
    val weatherDescription: String
)
