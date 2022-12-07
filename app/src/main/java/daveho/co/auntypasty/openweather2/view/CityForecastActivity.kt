package daveho.co.auntypasty.openweather2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import daveho.co.auntypasty.openweather2.R
import daveho.co.auntypasty.openweather2.databinding.ActivityCityForecastBinding
import daveho.co.auntypasty.openweather2.viewmodel.*

@AndroidEntryPoint
class CityForecastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityForecastBinding
    private lateinit var adapter: CityForecastViewAdapter
    //private lateinit var cityForecastViewModel: CityForecastViewModel
    private val cityForecastViewModel: CityForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_forecast)

        binding.forecastList.addItemDecoration(ListDividerItemDecoration(this))

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