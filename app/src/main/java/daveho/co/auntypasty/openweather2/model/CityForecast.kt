package daveho.co.auntypasty.openweather2.model

data class CityForecast(
    val id: Int,
    val cityName: String,
    val country: String,
    val weatherList: List<WeatherList>
)
