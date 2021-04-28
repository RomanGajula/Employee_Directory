package com.example.employee_directory.api

import com.example.employee_directory.utils.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

//    private val retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }

//    https://rawgit.com/startandroid/data/master/messages/

    var gson = GsonBuilder()
        .setLenient()
        .create()


    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var api: Api = retrofit.create(Api::class.java)
}