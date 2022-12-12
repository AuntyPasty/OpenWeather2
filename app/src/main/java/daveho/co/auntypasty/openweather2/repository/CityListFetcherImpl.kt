package daveho.co.auntypasty.openweather2.repository

import daveho.co.auntypasty.openweather2.Utils
import daveho.co.auntypasty.openweather2.model.CitySummary
import daveho.co.auntypasty.openweather2.model.WeatherList
import daveho.co.auntypasty.openweather2.network.ApiService
import java.util.*
import javax.inject.Inject

class CityListFetcherImpl @Inject constructor(
    val apiService: ApiService
): CityListFetcher {

    override suspend fun findCitiesWithSearchString(searchString: String): List<CitySummary> {
        val response = apiService.citySearch(Utils.API_KEY, "metric", searchString)

        return if (!response.list.isNullOrEmpty()) {
            response.list.map {
                it.toDomain()
            }
        } else {
            emptyList()
        }
    }

    private fun WeatherList.toDomain(): CitySummary = CitySummary(
        id = id ?: 0,
        cityName = name ?: "Unknown name",
        country = Utils.getCountryStringFromCode(sys!!.country),
        weatherTime = dt ?: 0,
        tempCelcius = main!!.temp ?: 0f,
        windSpeedMps = wind!!.speed ?: 0f,
        windDirection = wind!!.deg ?: 0f,
        weatherDescription = weather!![0].description ?: "Unknown description"
    )

}