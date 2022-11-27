package daveho.co.auntypasty.openweather2.network

import daveho.co.auntypasty.openweather2.model.CityForecastResponse
import daveho.co.auntypasty.openweather2.model.CitySummaryListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("find?type=like")
    suspend fun citySearch(@Query("appid") appId: String, @Query("units") units: String, @Query("q") searchString: String): CitySummaryListResponse

    @GET("forecast&appid=" + RetrofitClientInstance.API_KEY + "&units=metric&id={id}")
    suspend fun cityForecast(@Path("id") id: Int): CityForecastResponse
}