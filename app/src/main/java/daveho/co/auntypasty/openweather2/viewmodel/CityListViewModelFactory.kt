package daveho.co.auntypasty.openweather2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import daveho.co.auntypasty.openweather2.repository.CityListFetcherImpl
import java.lang.IllegalArgumentException

class CityListViewModelFactory(
    private val repository: CityListFetcherImpl
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityListViewModel::class.java)) {
            return CityListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}