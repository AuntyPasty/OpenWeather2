package daveho.co.auntypasty.openweather2.viewmodel

import androidx.lifecycle.*
import daveho.co.auntypasty.openweather2.Direction
import daveho.co.auntypasty.openweather2.Utils
import daveho.co.auntypasty.openweather2.model.CitySummary
import daveho.co.auntypasty.openweather2.repository.CityListFetcherImpl
import kotlinx.coroutines.launch
import java.util.*

class CityListViewModel(private val cityListFetcher: CityListFetcherImpl): ViewModel() {

    private val liveCityList = MutableLiveData<List<CitySummaryModel>>()
    val cityListData : LiveData<List<CitySummaryModel>>
        get() = liveCityList

    init {
        // Do we need to do anything here?
    }

    fun searchCityWithString(searchString: String) = viewModelScope.launch {
        //TODO How do we cancel this job?

        if (searchString.length >= 3) {
            val cityList = cityListFetcher.findCitiesWithSearchString(searchString)
                .map {
                    it.toModel()
                }

            liveCityList.postValue(cityList)
        }
    }

    private fun CitySummary.toModel() = CitySummaryModel(
        id = id,
        cityName = cityName,
        country = country,
        weatherTime = Utils.convertEpocToDateString(weatherTime.toLong()),
        celciusTemp = Utils.formatTemperatureToString(tempCelcius),
        windSpeed = Utils.convertMetresPerSecToMPH(windSpeedMps),
        weatherDescription = weatherDescription.substring(0, 1).uppercase(Locale.getDefault()) + weatherDescription.substring(1),
        windDirection = windDegreeToDirection(windDirection)
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