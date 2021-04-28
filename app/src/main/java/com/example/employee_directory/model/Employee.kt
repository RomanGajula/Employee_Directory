package com.example.employee_directory.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Employee(
    @SerializedName("id")
    @Expose
    private val id: String?,

    @SerializedName("employee_name")
    @Expose
    private val employeeName: String?,

    @SerializedName("employee_salary")
    @Expose
    private val employeeSalary: String?,

    @SerializedName("employee_age")
    @Expose
    private val employeeAge: String?,

    @SerializedName("profile_image")
    @Expose
    private val profileImage: String?
)

