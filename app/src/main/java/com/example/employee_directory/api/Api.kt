package com.example.employee_directory.api

import com.example.employee_directory.model.Employee
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Api {
    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost(
        @Body employee: Employee
    ): Response<Employee>

    @Headers("Accept: application/json")
    @GET("employees")
    fun getData(): Call<Employee>

    @Headers("Accept: application/json")
    @GET("employees")
    fun getEmployee(): Call<List<Employee>>

//    /api/v1/employees
}