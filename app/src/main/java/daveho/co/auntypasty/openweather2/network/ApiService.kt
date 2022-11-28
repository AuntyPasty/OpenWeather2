package daveho.co.auntypasty.openweather2.network

import daveho.co.auntypasty.openweather2.model.CityForecastResponse
import daveho.co.auntypasty.openweather2.model.CitySummaryListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("find?type=like")
    suspend fun citySearch(@Query("appid") appId: String, @Query("units") units: String, @Query("q") searchString: String): CitySummaryListResponse

    @GET("forecast")
    suspend fun cityForecast(@Query("appid") appId: String, @Query("units") units: String, @Query("id") id: Int): CityForecastResponse
}