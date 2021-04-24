package com.example.employee_directory.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("id")
    private val id: String?,

    @SerializedName("employee_name")
    private val employeeName: String?,

    @SerializedName("employee_salary")
    private val employeeSalary: String?,

    @SerializedName("employee_age")
    private val employeeAge: String?,

    @SerializedName("profile_image")
    private val profileImage: String?
)

