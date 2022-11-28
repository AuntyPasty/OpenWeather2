package daveho.co.auntypasty.openweather2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import daveho.co.auntypasty.openweather2.Utils
import daveho.co.auntypasty.openweather2.model.Direction
import daveho.co.auntypasty.openweather2.model.WeatherList
import daveho.co.auntypasty.openweather2.repository.CityForecastFetcherImpl
import kotlinx.coroutines.launch

class CityForecastViewModel(private val cityForecastFetcher: CityForecastFetcherImpl): ViewModel() {

    private val liveCityForecast = MutableLiveData<CityForecastModel>()
    val cityForecastData : LiveData<CityForecastModel>
        get() = liveCityForecast

    init {
        // Do we need to do anything here?
    }

    fun getForecast(cityId: Int) = viewModelScope.launch {
        //TODO How do we cancel this job?

        val forecastResponse = cityForecastFetcher.getCityForecast(cityId)
        val weatherList = forecastResponse.weatherList
            .map {
                it.toModel()
            }

        val cityForecast = CityForecastModel(
            forecastResponse.id,
            forecastResponse.cityName,
            forecastResponse.country,
            weatherList
        )

        liveCityForecast.postValue(cityForecast)
    }

    private fun WeatherList.toModel() = WeatherModel(
        date = Utils.convertEpocToDateString(dt!!.toLong()),
        windSpeed = Utils.convertMetresPerSecToMPH(wind!!.speed),
        temperature = Utils.formatTemperatureToString(main!!.temp),
        description = weather!![0].description?: "Unknown description",
        windDirection = Utils.windDegreeToDirection(wind!!.deg?: 0f)
    )

    private fun windDegreeToDirection(windDegree: Float): Direction {

        // Calculate which icon to show based on the angle of the wind
        val compassDirection = when {
            (windDegree <= 22.5) -> Direction.NORTH
            (windDegree > 22.5 && windDegree <= 67.5) -> Direction.NORTHEAST
            (windDegree > 67.5 && windDegree <= 112.5) -> Direction.EAST
            (windDegree > 112.5 && windDegree <= 157.5) -> Direction.SOUTHEAST
            (windDegree > 157.5 && windDegree <= 202.5) -> Direction.SOUTH
            (windDegree > 202.5 && windDegree <= 247.5) -> Direction.SOUTHWEST
            (windDegree > 247.5 && windDegree <= 292.5) -> Direction.WEST
            (windDegree > 292.5 && windDegree <= 337.5) -> Direction.NORTHWEST
            else -> Direction.NORTH
        }
        return compassDirection
    }
}