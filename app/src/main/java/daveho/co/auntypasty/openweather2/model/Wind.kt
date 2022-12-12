package daveho.co.auntypasty.openweather2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wind (
    @SerializedName("speed")
    @Expose
    var speed: Float? = null,
    @SerializedName("deg")
    @Expose
    var deg: Float? = null
)