package daveho.co.auntypasty.openweather2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import daveho.co.auntypasty.openweather2.repository.CityForecastFetcherImpl
import java.lang.IllegalArgumentException

class CityForecastViewModelFactory(
    private val repository: CityForecastFetcherImpl
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityForecastViewModel::class.java)) {
            return CityForecastViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}