package daveho.co.auntypasty.openweather2.viewmodel

import daveho.co.auntypasty.openweather2.Direction

data class WeatherModel(
    val date: String,
    val windSpeed: String,
    val temperature: String,
    val description: String,
    val windDirection: Direction
)
