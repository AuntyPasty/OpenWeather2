package daveho.co.auntypasty.openweather2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CitySummaryListResponse(

    @SerializedName("message")
    @Expose
    val message: String?,
    @SerializedName("cod")
    @Expose
    val cod: String?,
    @SerializedName("count")
    @Expose
    val count: Int?,
    @SerializedName("list")
    @Expose
    val list: List<WeatherList>?
)
