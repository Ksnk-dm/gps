package com.example.gps.data.repository

import androidx.lifecycle.LiveData
import com.example.gps.data.ObjDataBase
import com.example.gps.data.dao.ObjDao
import com.example.gps.data.entity.Obj
import javax.inject.Inject

class ObjRepository @Inject constructor(db: ObjDataBase) {
    private var objDao: ObjDao? = db.objDao()

    fun insert(obj: Obj) = objDao?.insert(obj)

    fun findAll(): LiveData<List<Obj>>? = objDao?.findAll()
}