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

    val cityName = MutableLiveData<String>()
    val countryName = MutableLiveData<String>()

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

        cityName.value = forecastResponse.cityName
        countryName.value = forecastResponse.country

        liveCityForecast.postValue(cityForecast)
    }

    private fun WeatherList.toModel() = WeatherModel(
        date = Utils.convertEpocToDateString(dt!!.toLong()),
        windSpeed = Utils.convertMetresPerSecToMPH(wind!!.speed),
        temperature = Utils.formatTemperatureToString(main!!.temp),
        description = weather!![0].description?: "Unknown description",
        windDirection = Utils.windDegreeToDirection(wind!!.deg?: 0f)
    )

}