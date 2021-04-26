package com.example.employee_directory.repository

import com.example.employee_directory.R
import com.example.employee_directory.api.Api
import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.model.Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class Repository {
    suspend fun pushPost(data: Data) : Response<Data> {
        return RetrofitInstance.api.pushPost(data)
    }

    fun getData(): Call<List<Data>> {
        return RetrofitInstance.api.getData()
    }
}