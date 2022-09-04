package com.example.jetpackcomposewithmvvmandretorfit.di

import com.example.jetpackcomposewithmvvmandretorfit.retrofit.ApiService
import com.example.jetpackcomposewithmvvmandretorfit.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesApiService():ApiService =
        Retrofit.Builder()
            .run {
                baseUrl(Constants.BASE_URL)
                    addConverterFactory(GsonConverterFactory.create())
                build()
            }.create(ApiService::class.java)

}