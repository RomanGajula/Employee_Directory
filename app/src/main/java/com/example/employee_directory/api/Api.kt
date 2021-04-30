package com.example.employee_directory.api

import com.example.employee_directory.model.Employee
import com.example.employee_directory.model.GetRequest
import com.example.employee_directory.model.PostRequest
import retrofit2.Call
import retrofit2.http.*


interface Api {
    @POST("employees")
    fun addEmployee(
            @Body employee: Employee
    ): Call<Employee>

//    @Headers("Accept: application/json")
//    @GET("/api/v1/employees")
//    fun getData(): Call<Employee>

    @GET("employees")
    fun getEmployee(): Call<MutableList<Employee>>

    @DELETE("employees/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<Void>

    fun onItemDeleted(employee: Employee, position: Int)
//    /api/v1/employees
}