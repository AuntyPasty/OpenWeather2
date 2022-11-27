package daveho.co.auntypasty.openweather2.repository

import daveho.co.auntypasty.openweather2.model.CityForecast
import daveho.co.auntypasty.openweather2.model.CityForecastResponse
import daveho.co.auntypasty.openweather2.network.RetrofitClientInstance
import java.util.*

class CityForecastFetcherImpl {
    suspend fun getCityForecast(id: Int): CityForecast {
        val response = RetrofitClientInstance.apiService.cityForecast(id)

        return response.toDomain()

    }

    private fun CityForecastResponse.toDomain() = CityForecast(
        id = city!!.id ?: 0,
        cityName = city!!.name ?: "Unknown city name",
        country = getCountryStringFromCode(city!!.country?: ""),
        weatherList = list ?: emptyList(),
    )

    /**
     * Takes a country code and converts it to the country name string
     * @param code country code
     * @return country name string
     */
    private fun getCountryStringFromCode(code: String?): String {

        return if (code != null) {
            val locale = Locale("", code)

            locale.displayCountry
        } else {
            "Unknown"
        }
    }
}