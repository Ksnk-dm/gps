package com.example.gps.di

import android.content.Context
import com.example.gps.di.modules.DataBaseModule
import com.example.gps.di.modules.LocationModule
import com.example.gps.di.modules.RetroFitModule
import com.example.gps.ui.main.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetroFitModule::class, DataBaseModule::class, LocationModule::class]
)

interface AppComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)
    fun database(context: Context)
    fun location(context: Context)
}