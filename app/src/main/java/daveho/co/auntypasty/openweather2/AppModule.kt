package daveho.co.auntypasty.openweather2

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import daveho.co.auntypasty.openweather2.network.ApiService
import daveho.co.auntypasty.openweather2.repository.CityForecastFetcher
import daveho.co.auntypasty.openweather2.repository.CityForecastFetcherImpl
import daveho.co.auntypasty.openweather2.repository.CityListFetcher
import daveho.co.auntypasty.openweather2.repository.CityListFetcherImpl

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesCityForecastFetcher(api: ApiService): CityForecastFetcher = CityForecastFetcherImpl(api)

    @Provides
    fun providesCityListFetcher(api: ApiService): CityListFetcher = CityListFetcherImpl(api)

}