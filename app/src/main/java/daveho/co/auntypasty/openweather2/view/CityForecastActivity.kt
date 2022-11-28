package daveho.co.auntypasty.openweather2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import daveho.co.auntypasty.openweather2.R
import daveho.co.auntypasty.openweather2.databinding.ActivityCityForecastBinding
import daveho.co.auntypasty.openweather2.repository.CityForecastFetcherImpl
import daveho.co.auntypasty.openweather2.repository.CityListFetcherImpl
import daveho.co.auntypasty.openweather2.viewmodel.*

class CityForecastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityForecastBinding
    private lateinit var adapter: CityForecastViewAdapter
    private lateinit var cityForecastViewModel: CityForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_forecast)

        binding.forecastList.addItemDecoration(ListDividerItemDecoration(this))

        // Improve with DI
        val repository = CityForecastFetcherImpl()
        val factory = CityForecastViewModelFactory(repository)
        cityForecastViewModel = ViewModelProvider(this, factory)[CityForecastViewModel::class.java]
        binding.forecastModel = cityForecastViewModel
        binding.lifecycleOwner = this

        val intent = intent
        val cityId = intent.getIntExtra("city_id", 0)

        setupForecastListDisplay()

        cityForecastViewModel.getForecast(cityId)
    }

    private fun setupForecastListDisplay() {
        binding.forecastList.layoutManager = LinearLayoutManager(this)
        adapter = CityForecastViewAdapter()
        binding.forecastList.adapter = adapter
        displayForecast()
    }
    private fun displayForecast() {
        cityForecastViewModel.cityForecastData.observe(this) {
            adapter.setForecastList(it.weatherList)
            adapter.notifyDataSetChanged()
        }
    }
}