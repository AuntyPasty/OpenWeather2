package daveho.co.auntypasty.openweather2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Main (

    @SerializedName("temp")
    @Expose
    var temp: Float? = null,
    @SerializedName("pressure")
    @Expose
    var pressure: Float? = null,
    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null,
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double? = null,
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double? = null,
    @SerializedName("sea_level")
    @Expose
    var seaLevel: Double? = null,
    @SerializedName("grnd_level")
    @Expose
    var grndLevel: Double? = null,
    @SerializedName("temp_kf")
    @Expose
    var tempKf: Float? = null
)