package com.example.jornadaandroid2023.model.source.remote.service

import com.example.jornadaandroid2023.model.source.remote.entities.HintsApiResult
import retrofit2.Call
import retrofit2.http.GET

interface HintsService {
    @GET("tblCw2tYfnYbbgIel")
    fun listHints(): Call<HintsApiResult>
}