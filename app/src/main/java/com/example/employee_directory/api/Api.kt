package com.example.employee_directory.api

import com.example.employee_directory.model.Employee
import com.example.employee_directory.model.RequestData
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @POST("/api/v1/employees")
    fun addEmployee(
            @Body employee: Employee
    ): Call<Employee>

    @Headers("Accept: application/json")
    @GET("/api/v1/employees")
    fun getData(): Call<Employee>

    @GET("/api/v1/employees")
    fun getEmployee(): Call<RequestData>

//    /api/v1/employees
}