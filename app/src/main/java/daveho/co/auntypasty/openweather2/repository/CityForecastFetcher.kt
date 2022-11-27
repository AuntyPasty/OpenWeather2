package daveho.co.auntypasty.openweather2.repository

import daveho.co.auntypasty.openweather2.model.CityForecast

interface CityForecastFetcher {

    suspend fun getCityForecast(id: Int): CityForecast
}