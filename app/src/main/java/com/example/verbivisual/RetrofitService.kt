package com.example.verbivisual

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search")
    fun getImages(@Query("q")q:String): Call<Images>
}