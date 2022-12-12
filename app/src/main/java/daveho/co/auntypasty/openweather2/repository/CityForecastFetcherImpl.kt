package daveho.co.auntypasty.openweather2.repository

import daveho.co.auntypasty.openweather2.Utils
import daveho.co.auntypasty.openweather2.model.CityForecast
import daveho.co.auntypasty.openweather2.model.CityForecastResponse
import daveho.co.auntypasty.openweather2.network.ApiService
import java.util.*
import javax.inject.Inject

class CityForecastFetcherImpl @Inject constructor(
    val apiService: ApiService
): CityForecastFetcher {
    override suspend fun getCityForecast(id: Int): CityForecast {
        val response = apiService.cityForecast(Utils.API_KEY, "metric", id)

        return response.toDomain()
    }

    private fun CityForecastResponse.toDomain() = CityForecast(
        id = city!!.id ?: 0,
        cityName = city!!.name ?: "Unknown city name",
        country = Utils.getCountryStringFromCode(city!!.country?: "Unknown country"),
        weatherList = list ?: emptyList(),
    )
}