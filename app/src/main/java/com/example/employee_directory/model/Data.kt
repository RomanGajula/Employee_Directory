package com.example.employee_directory.model


import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback

data class Data(
    @SerializedName("employee_age")
    val employeeAge: String,
    @SerializedName("employee_name")
    val employeeName: String,
    @SerializedName("employee_salary")
    val employeeSalary: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("profile_image")
    val profileImage: String
)