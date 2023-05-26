package com.example.jornadaandroid2023.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jornadaandroid2023.view.treasure_hunt.MapsActivity
import com.example.jornadaandroid2023.R

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