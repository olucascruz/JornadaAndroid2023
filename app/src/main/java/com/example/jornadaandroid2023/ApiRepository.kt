package com.example.jornadaandroid2023

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository {

    private const val API_KEY = "patjuAoOFil6id2zw.c6383a281a1108a09e49c579b4065732e46b887881b6e9fda7e1657ea61b6b4d"

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer $API_KEY")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private val hintsService: HintsService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.airtable.com/v0/appouUwOyyKLH6Eyz/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        hintsService = retrofit.create(HintsService::class.java)
    }

    fun listHints(callback: HintCallback){
        val call = hintsService.listHints()

        call.enqueue(object: Callback<HintsApiResult>{
            override fun onResponse(call: Call<HintsApiResult>, response: Response<HintsApiResult>)
            {
                Log.d("API_REPOSITORY", "Resposta recebida com sucesso!")
                val body: HintsApiResult? = response.body()
                if(response.isSuccessful && body != null){
                    val records = body.records
                    val hints = records.map{it.fields}
                }

            }

            override fun onFailure(call: Call<HintsApiResult>, t: Throwable) {
                Log.e("API_REPOSITORY", "Resposta falhou!", t)

            }

        })
    }

}

interface HintCallback {
    fun onResult(hints: List<Hint>)
}