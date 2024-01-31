package com.example.verbivisual

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
val retrofitService :RetrofitService
    init{
        val retrofit=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(" https://lexica.art/api/v1/")
            .build()

       retrofitService= retrofit.create(RetrofitService::class.java)
    }
}