package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.mapper.PokemonRepositoryMapper
import com.example.data.remote.PokeApi
import com.example.data.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokeApi,
    ) = PokemonRepositoryImpl(api)


    @Singleton
    @Provides
    fun providePokeApi(): PokeApi {

        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client : OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(PokeApi::class.java)
    }


}