package com.example.pasi.Api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepository private constructor() {

    companion object {

        private var apiRepository: ApiRepository? = null
        val instance: ApiRepository
            get() {
                if (apiRepository == null) apiRepository = ApiRepository()
                return apiRepository!!
            }
    }

    fun sendText(
        to: String,
        text: String,
        request: Request,
    ) {
        RetrofitService.apiService.senTextToTelegram(to, text).enqueue(
            object : Callback<MainModel> {
                override fun onResponse(call: Call<MainModel>, response: Response<MainModel>) {
                    if (response.isSuccessful) {
                        val data = response.body() as MainModel
                        request.onSuccess(data)
                    } else
                        request.onNotSuccess("Not Success")
                }


                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    request.onError("Error")
                }

            }
        )

    }


}