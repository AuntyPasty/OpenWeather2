package daveho.co.auntypasty.openweather2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherList {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null
    @SerializedName("main")
    @Expose
    var main: Main? = null
    @SerializedName("dt")
    @Expose
    var dt: Int? = null
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null
    @SerializedName("sys")
    @Expose
    var sys: Sys? = null
    @SerializedName("rain")
    @Expose
    var rain: Any? = null
    @SerializedName("snow")
    @Expose
    var snow: Snow? = null
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null
    @SerializedName("weather")
    @Expose
    var weather: MutableList<Weather>? = null
    @SerializedName("dt_txt")
    @Expose
    var dtTxt: String? = null

}