package com.example.gps.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gps.data.dao.ObjDao
import com.example.gps.data.entity.Obj

@Database(entities = [Obj::class], version = 1)
abstract class ObjDataBase : RoomDatabase() {
    abstract fun objDao(): ObjDao

    companion object {

        @Volatile
        private var instance: ObjDataBase? = null

        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ObjDataBase::class.java,
                "data"
            ).allowMainThreadQueries().build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }
}