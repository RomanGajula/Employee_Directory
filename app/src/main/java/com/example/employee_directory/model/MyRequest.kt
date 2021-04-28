package com.example.employee_directory.model


import com.google.gson.annotations.SerializedName

data class MyRequest(
        @SerializedName("data")
    val `data`: List<Employee>,
        @SerializedName("status")
    val status: String
)