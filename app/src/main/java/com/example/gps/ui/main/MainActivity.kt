package com.example.gps.ui.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gps.R

class MainActivity : AppCompatActivity() {
    private var mMainActivityViewModel: MainActivityViewModel? = null
    private var textViewLocation: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mMainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewLocation = findViewById(R.id.textViewLocation)
        checkPermission()
        startLocationUpdate()
        startPostRequest()
    }

    private fun checkPermission() {
        val hasLocationPermission = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val fineLocationPermission = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasLocationPermission || fineLocationPermission) {
            Toast.makeText(this, "Location done", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 100
            )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100
            )
        }
    }

    private fun startLocationUpdate() {
        mMainActivityViewModel?.showLocationData()?.observe(this, Observer {
            mMainActivityViewModel?.insertObj(it)
            textViewLocation?.text = it.toString()
        })
    }

    private fun startPostRequest() {
        mMainActivityViewModel?.findAll()?.observe(this, Observer {
            if (it.size >= 10) {
                mMainActivityViewModel?.retroFitSendListToServer(it.takeLast(10))
            }
        })
    }

}