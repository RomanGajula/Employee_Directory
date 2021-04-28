package com.example.employee_directory.api

import com.example.employee_directory.model.Employee
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost(
        @Body employee: Employee
    ): Response<Employee>

    @GET("employees")
    fun getData(): Call<Employee>

//    /api/v1/employees
}