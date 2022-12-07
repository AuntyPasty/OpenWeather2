package daveho.co.auntypasty.openweather2

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import daveho.co.auntypasty.openweather2.repository.CityForecastFetcher
import daveho.co.auntypasty.openweather2.repository.CityForecastFetcherImpl
import daveho.co.auntypasty.openweather2.repository.CityListFetcher
import daveho.co.auntypasty.openweather2.repository.CityListFetcherImpl

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesCityForecastFetcher(): CityForecastFetcher = CityForecastFetcherImpl()

    @Provides
    fun providesCityListFetcher(): CityListFetcher = CityListFetcherImpl()

}