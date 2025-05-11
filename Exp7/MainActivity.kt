package com.example.ex7

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import android.location.Geocoder

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale


class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var ll: TextView
    private lateinit var addr: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ll = findViewById<TextView>(R.id.lola)
        addr = findViewById<TextView>(R.id.addr)
        val  getloc = findViewById<Button>(R.id.getLoc)

        if(ContextCompat.checkSelfPermission(applicationContext,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&
            ActivityCompat.checkSelfPermission(applicationContext,android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),101)
        }

        getloc.setOnClickListener @androidx.annotation.RequiresPermission(anyOf = [android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION]) {
            getLocation()
            Toast.makeText(this,"Location", Toast.LENGTH_SHORT).show()
        }

    }
//    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun getLocation()=try{
        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,5f,this)
    }catch(e: SecurityException){
        e.printStackTrace()
    }

    override fun onLocationChanged(location: Location) {
        val text = "Latitude:"+location.latitude+"\nLongitude:"+location.longitude
        ll.text = text
        try {
            val geocoder = Geocoder(this,Locale.getDefault())
            val address = geocoder.getFromLocation(location.latitude,location.longitude,1)
            addr.text = address?.get(0)?.getAddressLine(0)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==2){
            if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onProviderDisabled(provider: String) {
        Toast.makeText(this,"Please Enable GPS and Internet", Toast.LENGTH_SHORT).show()
    }

    @Deprecated("Deprecated in Java")
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String) {

    }

}