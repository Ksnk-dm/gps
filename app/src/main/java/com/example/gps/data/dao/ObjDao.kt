package com.example.gps.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gps.data.entity.Obj

@Dao
interface ObjDao {
    @Insert
    fun insert(vararg obj: Obj)

    @Query("SELECT * FROM obj")
    fun findAll(): LiveData<List<Obj>>
}