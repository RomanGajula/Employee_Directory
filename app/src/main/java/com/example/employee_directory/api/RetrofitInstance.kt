package com.example.employee_directory.api

import com.example.employee_directory.utils.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    var gson = GsonBuilder()
        .setLenient()
        .create()


    var retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var api: Api = retrofit.create(Api::class.java)
}