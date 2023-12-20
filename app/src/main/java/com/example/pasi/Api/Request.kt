package com.example.pasi.Api

interface Request {

    fun onSuccess(data:MainModel)
    fun onNotSuccess(message:String)
    fun onError(error:String)
}