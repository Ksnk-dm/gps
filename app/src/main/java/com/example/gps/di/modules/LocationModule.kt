package com.example.gps.di.modules

import android.app.Application
import com.example.gps.location.LocationLiveData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule(var application: Application) {
    @Singleton
    @Provides
    fun getLocationModule(): LocationLiveData {
        return LocationLiveData(application)
    }
}