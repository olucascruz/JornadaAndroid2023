package com.example.jornadaandroid2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HintsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hints_list)

        val rvHints = findViewById<RecyclerView>(R.id.rvHints)

        val layoutManager = LinearLayoutManager(this)
        rvHints.layoutManager = layoutManager

        val hintsList = listOf(
            Hint(1,"Local da dica", 10.2, 10.10) ,
            Hint(2,"Local da dica", 15.0, 10.30),
            Hint(3,"Local da dica", 14.0, 10.20)
        )

        val adapter = HintsListAdapter(hintsList)
        rvHints.adapter = adapter
    }
}