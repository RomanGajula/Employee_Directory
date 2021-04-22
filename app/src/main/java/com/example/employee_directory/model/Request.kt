package com.example.employee_directory.model


import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)