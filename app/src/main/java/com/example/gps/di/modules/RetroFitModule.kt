package com.example.gps.di.modules

import com.example.gps.api.ObjectsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetroFitModule {
    private val baseURL = "http://192.168.3.10:8082/"

    @Singleton
    @Provides
    fun getRetroServiceInterface(retrofit: Retrofit): ObjectsApi {
        return retrofit.create(ObjectsApi::class.java)
    }

    @Singleton
    @Provides
    fun getRetroFitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}