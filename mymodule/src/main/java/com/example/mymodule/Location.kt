package com.example.mymodule

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import java.util.*

class Location (activity : AppCompatActivity){

    val activity=activity
    var lat=MutableLiveData<Double>()
    var lng= MutableLiveData<Double>()

    @SuppressLint("MissingPermission")
    val requestMultiplePermissions= activity.activityResultRegistry.register("PERMISSION",ActivityResultContracts.RequestMultiplePermissions()){
        val fusedLocationClient= LocationServices.getFusedLocationProviderClient(activity)

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                lat.value=location.latitude
                lng.value=location.longitude
            }
        }
    }

    init{
        request()
    }

    fun request(){
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    fun getCountryCode(): String{
        val geocoder= Geocoder(activity.applicationContext, Locale.getDefault())
        var address=geocoder.getFromLocation(lat.value!!,lng.value!!,1)
        return address[0].countryCode
    }


}