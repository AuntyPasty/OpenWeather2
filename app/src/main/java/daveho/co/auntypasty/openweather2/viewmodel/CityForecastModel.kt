package daveho.co.auntypasty.openweather2.viewmodel

data class CityForecastModel (
    val id: Int,
    val cityName: String,
    val country: String,
    val weatherList: List<WeatherModel>
)