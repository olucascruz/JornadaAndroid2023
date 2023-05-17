package com.example.jornadaandroid2023

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.jornadaandroid2023.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    val apiRepository = ApiRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        binding.fab.setOnClickListener{
            val hintsListActivity = Intent(this, HintsListActivity::class.java, )
            startActivity(hintsListActivity)
        }
    }


     //Manipulates the map once available.

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val masp = LatLng(-23.56145468796075, -46.65589196747173)
        mMap.addMarker(MarkerOptions().position(masp).title("MASP (São Paulo)"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(masp))


        startLocationService()


        apiRepository.listHints(object : HintCallback {
            override fun onResult(hints: List<Hint>) {
                hints.forEach { hint ->
                    val marker = LatLng(hint.latitude, hint.longitude)
                    mMap.addMarker(
                        MarkerOptions()
                            .position(marker)
                            .title("Dica ${hint.id}: ${hint.name}")
                    )
                }
            }
        })

    }


    private fun startLocationService() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Exibe uma mensagem solicitando a permissão da localização
            Toast.makeText(this, "Permita a localização.", Toast.LENGTH_LONG).show()
            requestLocationPermission()

            // Encerra a execução da função, pois não temos permissão para ver a localização
            return
        }

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationProvider = LocationManager.GPS_PROVIDER

        val currentLocation = locationManager.getLastKnownLocation(locationProvider)

        if(currentLocation == null){
            Toast.makeText(this, "habilite a localização",Toast.LENGTH_LONG).show()
            return
        }

        val currentLocationLatLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val zoomLevel = 25.0f
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLocationLatLng, zoomLevel)
        mMap.animateCamera(cameraUpdate)
        mMap.addMarker(MarkerOptions().position(currentLocationLatLng).title("Você esta aqui"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocationLatLng))
    }

    private fun requestLocationPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf (Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            , 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1 &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED ||
            grantResults[1] == PackageManager.PERMISSION_GRANTED){

            startLocationService()
        }
    }
}