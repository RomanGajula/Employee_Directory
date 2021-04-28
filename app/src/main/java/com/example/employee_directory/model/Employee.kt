package com.example.employee_directory.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Employee(
    @SerializedName("id")
    @Expose
    val id: String?,

    @SerializedName("employee_name")
    @Expose
    val employeeName: String?,

    @SerializedName("employee_salary")
    @Expose
    val employeeSalary: String?,

    @SerializedName("employee_age")
    @Expose
    val employeeAge: String?,

    @SerializedName("profile_image")
    @Expose
    val profileImage: String?
)

