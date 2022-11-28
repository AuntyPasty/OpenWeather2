package daveho.co.auntypasty.openweather2

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.google.gson.Gson
import daveho.co.auntypasty.openweather2.ApplicationModule.applicationContext
import daveho.co.auntypasty.openweather2.model.Direction
import org.json.JSONObject
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    private val TAG = Utils::class.java.getSimpleName()

    /**
     * Use Connectivity Manager to detect whether the app is connected to a network
     * regardless of connection type.
     * @return true if connected to network
     */
    val isConnected: Boolean
        get() {

            var networkInfo: NetworkInfo?

            val connectivityManager = applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            networkInfo = connectivityManager.activeNetworkInfo


            return networkInfo != null && networkInfo.isConnected
        }

    /**
     * Takes an epos value and converts it to a human readable date string
     * @param epocTime epoc time in seconds
     * @return date string
     */
    fun convertEpocToDateString(epocTime: Long): String {
        // The incoming epoc time is in seconds so we need it in milliseconds.
        val date = Date(epocTime * 1000)

        val sdf = SimpleDateFormat("EEE MMM d, h:mm a")
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        return sdf.format(date)
    }

    /**
     * Takes a country code and converts it to the country name string
     * @param code country code
     * @return country name string
     */
    fun getCountryStringFromCode(code: String?): String {

        return if (code != null) {
            val locale = Locale("", code)

            locale.displayCountry
        } else {
            "Unknown"
        }
    }

    /**
     * Simple utility to convert speed in m/s to mph which is usually used in the UK
     * @param speed speed in m/s
     * @return speed string in mph
     */
    fun convertMetresPerSecToMPH(speed: Float?): String {

        val decimalFormat = DecimalFormat("###.#")
        return if (speed != null) {
            decimalFormat.format(speed * 2.236936) + " mph"
        } else {
            "0 mph"
        }
    }

    /**
     * Utility to format the temperature string to the format of the temperature value
     */
    fun formatTemperatureToString(temperature: Float?): String {

        val decimalFormat = DecimalFormat("###.#")
        return if (temperature != null) {
            decimalFormat.format(temperature)
        } else {
            ""
        }
    }

    fun windDegreeToDirection(windDegree: Float): Direction {

        // Calculate which icon to show based on the angle of the wind
        val compassDirection = when {
            (windDegree <= 22.5) -> Direction.NORTH
            (windDegree > 22.5 && windDegree <= 67.5) -> Direction.NORTHEAST
            (windDegree > 67.5 && windDegree <= 112.5) -> Direction.EAST
            (windDegree > 112.5 && windDegree <= 157.5) -> Direction.SOUTHEAST
            (windDegree > 157.5 && windDegree <= 202.5) -> Direction.SOUTH
            (windDegree > 202.5 && windDegree <= 247.5) -> Direction.SOUTHWEST
            (windDegree > 247.5 && windDegree <= 292.5) -> Direction.WEST
            (windDegree > 292.5 && windDegree <= 337.5) -> Direction.NORTHWEST
            else -> Direction.NORTH
        }
        return compassDirection
    }
}