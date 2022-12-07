package daveho.co.auntypasty.openweather2.repository

import daveho.co.auntypasty.openweather2.model.CitySummary
import daveho.co.auntypasty.openweather2.model.WeatherList
import daveho.co.auntypasty.openweather2.network.RetrofitClientInstance
import java.time.Instant
import java.util.*

class CityListFetcherImpl: CityListFetcher {

    override suspend fun findCitiesWithSearchString(searchString: String): List<CitySummary> {
        val response = RetrofitClientInstance.apiService.citySearch(RetrofitClientInstance.API_KEY, "metric", searchString)

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
        country = getCountryStringFromCode(sys!!.country),
        weatherTime = dt ?: 0,
        tempCelcius = main!!.temp ?: 0f,
        windSpeedMps = wind!!.speed ?: 0f,
        windDirection = wind!!.deg ?: 0f,
        weatherDescription = weather!![0].description ?: "Unknown description"
    )

    /**
     * Takes a country code and converts it to the country name string
     * @param code country code
     * @return country name string
     */
    fun getCountryStringFromCode(code: String?): String {

        return if (code != null) {
            val locale = Locale("", code)

            locale.displayCountry
        } else {
            "Unknown"
        }
    }
}