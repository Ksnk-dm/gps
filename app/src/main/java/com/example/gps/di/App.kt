package com.example.gps.di

import android.app.Application
import com.example.gps.di.modules.DataBaseModule
import com.example.gps.di.modules.LocationModule
import com.example.gps.di.modules.RetroFitModule

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .retroFitModule(RetroFitModule())
            .dataBaseModule(DataBaseModule(this))
            .locationModule(LocationModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}