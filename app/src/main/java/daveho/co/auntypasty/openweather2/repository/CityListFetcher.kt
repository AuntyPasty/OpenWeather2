package daveho.co.auntypasty.openweather2.repository

import daveho.co.auntypasty.openweather2.model.CitySummary

interface CityListFetcher {
    suspend fun findCitiesWithSearchString(searchString: String): List<CitySummary>
}