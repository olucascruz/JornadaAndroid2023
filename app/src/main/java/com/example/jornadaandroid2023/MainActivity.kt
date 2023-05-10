package com.example.jornadaandroid2023

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openMapsActivity();
    }

    private fun openMapsActivity() {
        val mapsActivity = Intent(this, MapsActivity::class.java)
        startActivity(mapsActivity)
    }
}