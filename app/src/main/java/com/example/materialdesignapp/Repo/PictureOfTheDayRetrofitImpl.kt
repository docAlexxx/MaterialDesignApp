package com.example.materialdesignapp.Repo

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PictureOfTheDayRetrofitImpl {

    private val baseUrl = "https://api.nasa.gov/"

    fun getRetrofitImpl():PictureOfTheDayAPI{
        val podRetrofitImpl = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return podRetrofitImpl.create(PictureOfTheDayAPI::class.java)
    }
}