package com.example.coinapp.di

import com.example.coinapp.repository.CoinRepository
import com.example.coinapp.service.CoinAPI
import com.example.coinapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCryptoRepository(
        api: CoinAPI
    ) = CoinRepository(api)

    @Singleton
    @Provides
    fun provideCryptoApi(): CoinAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CoinAPI::class.java)
    }
}