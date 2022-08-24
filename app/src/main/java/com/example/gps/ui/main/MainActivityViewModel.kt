package com.example.gps.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gps.location.LocationLiveData
import com.example.gps.api.ObjectsApi
import com.example.gps.data.entity.Obj
import com.example.gps.data.repository.ObjRepository
import com.example.gps.di.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var objectsApi: ObjectsApi

    @Inject
    lateinit var objRepository: ObjRepository

    @Inject
    lateinit var locationData: LocationLiveData


    private var myCompositeDisposable: CompositeDisposable? = null

    init {
        (application as App).getAppComponent().inject(this)
        myCompositeDisposable = CompositeDisposable()
    }

    fun retroFitSendListToServer(listObj: List<Obj>) {
        myCompositeDisposable?.add(
            objectsApi.postObj(listObj)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onResponse, this::onFailure)
        )
    }

    fun showLocationData() = locationData

    fun insertObj(obj: Obj) {
        objRepository.insert(obj)
    }

    fun findAll(): LiveData<List<Obj>>? = objRepository.findAll()

    private fun onFailure(t: Throwable) {
        Log.d("error", t.toString())
    }

    private fun onResponse(response: ResponseBody) {
    }

}