package com.example.pasi.Api

import com.example.pasi.Api.MainModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("send")
    fun senTextToTelegram(
        @Query("to") token: String,
        @Query("text") message: String,
    ): Call<MainModel>

}