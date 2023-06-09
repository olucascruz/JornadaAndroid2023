package com.example.jornadaandroid2023.model.repository

import androidx.lifecycle.MutableLiveData
import com.example.jornadaandroid2023.model.domain.Hint
import com.example.jornadaandroid2023.model.source.remote.HintCallback
import com.example.jornadaandroid2023.model.source.remote.HintsRemoteDataSource
import com.example.jornadaandroid2023.model.source.remote.entities.HintApiModel

object HintsRepository {
    private val hintsRemoteDataSource = HintsRemoteDataSource
    val hints = MutableLiveData<List<Hint>>()


    init {
        hintsRemoteDataSource.listHints(object : HintCallback {
            override fun onResult(hintApiModels: List<HintApiModel>) {
                val hintsDomain = hintApiModels.map {
                    Hint(it.id, it.name, it.latitude, it.longitude)
                }

                hints.postValue(hintsDomain)
            }
        })
    }
}