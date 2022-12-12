package daveho.co.auntypasty.openweather2.repository

import daveho.co.auntypasty.openweather2.Utils
import daveho.co.auntypasty.openweather2.model.*
import daveho.co.auntypasty.openweather2.network.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class CityListFetcherImplTest {

    private val dummyCityListResponse = CitySummaryListResponse(
        message = "My message",
        cod = "my cod",
        count = 2,
        list = emptyList()
    )

    private val mockAPI = mock<ApiService> {
        onBlocking {
            citySearch(
                any(),
                any(),
                any()
            )
        } doReturn dummyCityListResponse
    }

    @Test
    fun `test calling api converts response to domain object`() = runBlocking {

        val expectedResult = emptyList<CitySummary>()

        val sut = CityListFetcherImpl(mockAPI)
        val result = sut.findCitiesWithSearchString("abc")

        assertEquals(expectedResult, result)
    }
}