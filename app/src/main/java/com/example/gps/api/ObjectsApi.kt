package com.example.gps.api


import com.example.gps.data.entity.Obj
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ObjectsApi {
    @POST("/add")
    fun postObj(@Body objRequest: List<Obj>): Observable<ResponseBody>
}
