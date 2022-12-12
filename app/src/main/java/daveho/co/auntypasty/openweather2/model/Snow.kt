package daveho.co.auntypasty.openweather2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Snow (
    @SerializedName("3h")
    @Expose
    var h: Double? = null
)