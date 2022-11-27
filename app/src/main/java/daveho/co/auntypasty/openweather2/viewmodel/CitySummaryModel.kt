package daveho.co.auntypasty.openweather2.viewmodel

import daveho.co.auntypasty.openweather2.Direction

data class CitySummaryModel(
    val id: Int,
    val cityName: String,
    val country: String,
    val weatherTime: String,
    val celciusTemp: String,
    val windSpeed: String,
    val windDirection: Direction,
    val weatherDescription: String
)