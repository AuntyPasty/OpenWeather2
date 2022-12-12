package daveho.co.auntypasty.openweather2.repository

import daveho.co.auntypasty.openweather2.Utils
import daveho.co.auntypasty.openweather2.model.*
import daveho.co.auntypasty.openweather2.network.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class CityForecastFetcherImplTest {

    private val dummyCityForecastResponse = CityForecastResponse(
        cod = "myCod",
        message = 12345.0,
        cnt = 0,
        list = emptyList(),
        city = City()
        )

    private val mockAPI = mock<ApiService> { onBlocking { cityForecast(any(), any(), any())} doReturn dummyCityForecastResponse}

    @Test
    fun `test calling api converts response to domain object` () = runBlocking {

        val expectedResult = CityForecast(
            id = 0,
            cityName = "Unknown city name",
            country = "UNKNOWN COUNTRY",
            weatherList = emptyList(),
        )
        val sut = CityForecastFetcherImpl(mockAPI)
        val result = sut.getCityForecast(1)

        Assert.assertEquals(expectedResult, result)
    }
}