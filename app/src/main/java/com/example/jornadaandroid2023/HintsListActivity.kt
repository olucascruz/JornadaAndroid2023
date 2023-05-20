package com.example.jornadaandroid2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jornadaandroid2023.database.AppDatabase
import com.example.jornadaandroid2023.database.HintEntity

class HintsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hints_list)

        val hintEntity1 = HintEntity(1, "brigadeiro", 10.0, 50.10)
        val hintEntity2 = HintEntity(2, "briga", 19.0, 52.10)
        val hintEntity3 = HintEntity(3, "deiro", 16.0, 54.10)
        val hintEntity4 = HintEntity(4, "gade", 14.0, 56.10)


        val appDatabase = AppDatabase.getInstance(this)
        val hintDao = appDatabase.hintDao()

        Thread {
            hintDao.insert(hintEntity1)
            hintDao.insert(hintEntity2)
            hintDao.insert(hintEntity3)
            hintDao.insert(hintEntity4)

            val hintsEntities = hintDao.findAll()

            runOnUiThread {
                val rvHints = findViewById<RecyclerView>(R.id.rvHints)

                val layoutManager = LinearLayoutManager(this)
                rvHints.layoutManager = layoutManager

                //val hintsList = listOf(
                //Hint(1,"Local da dica", 10.2, 10.10) ,
                //Hint(2,"Local da dica", 15.0, 10.30),
                //Hint(3,"Local da dica", 14.0, 10.20)
                //)

                val hintsList = hintsEntities.map {
                    Hint(it.id, it.name, it.latitude, it.longitude)
                }

                val adapter = HintsListAdapter(hintsList)
                rvHints.adapter = adapter
            }
        }.start()

        }


}