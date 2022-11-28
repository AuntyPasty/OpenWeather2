package daveho.co.auntypasty.openweather2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityForecastResponse(
    @SerializedName("cod")
    @Expose
    val cod: String?,
    @SerializedName("message")
    @Expose
    var message: Double?,
    @SerializedName("cnt")
    @Expose
    var cnt: Int?,
    @SerializedName("list")
    @Expose
    var list: List<WeatherList>?,
    @SerializedName("city")
    @Expose
    var city: City?
)
