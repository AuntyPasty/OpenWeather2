package daveho.co.auntypasty.openweather2

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.google.gson.Gson
import daveho.co.auntypasty.openweather2.ApplicationModule.applicationContext
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

//    /**
//     * Takes a json opject and converts it to OpenWeatherSearchData object
//     * @param jsonObject json object
//     * @return OpenWeatherSearchData object
//     */
//    fun convertJsonToSearchData(jsonObject: JSONObject): OpenWeatherSearchData? {
//
//        val gson = Gson()
//        var searchData: OpenWeatherSearchData? = null
//
//        try {
//            val jsonString = jsonObject.toString()
//            searchData = gson.fromJson<OpenWeatherSearchData>(jsonString, OpenWeatherSearchData::class.java)
//        } catch (e: Exception) {
//            Log.e(TAG, "Failed to convert Json")
//        }
//
//        return searchData
//    }
//
//    /**
//     * Takes a json opject and converts it to OpenWeatherForecastData object
//     * @param jsonObject json object
//     * @return OpenWeatherForecastData object
//     */
//    fun convertJsonToForecastData(jsonObject: JSONObject): OpenWeatherForecastData? {
//
//        val gson = Gson()
//        var forecastData: OpenWeatherForecastData? = null
//
//        try {
//            val jsonString = jsonObject.toString()
//            forecastData = gson.fromJson<OpenWeatherForecastData>(jsonString, OpenWeatherForecastData::class.java)
//        } catch (e: Exception) {
//            Log.e(TAG, "Failed to convert Json")
//        }
//
//        return forecastData
//    }

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
}