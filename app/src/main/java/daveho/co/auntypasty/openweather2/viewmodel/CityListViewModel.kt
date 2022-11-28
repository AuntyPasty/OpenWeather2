package daveho.co.auntypasty.openweather2.viewmodel

import androidx.lifecycle.*
import daveho.co.auntypasty.openweather2.model.Direction
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
        windDirection = Utils.windDegreeToDirection(windDirection)
    )
}