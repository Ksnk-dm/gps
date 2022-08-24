package com.example.gps.di.modules

import android.app.Application
import com.example.gps.data.ObjDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(var application: Application?) {
    @Provides
    @Singleton
    fun providesAppDatabase(): ObjDataBase {
        return ObjDataBase.buildDatabase(application?.applicationContext!!)
    }
}