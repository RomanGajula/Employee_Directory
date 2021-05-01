package com.example.employee_directory.repository

import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.model.Employee
import org.koin.core.KoinComponent
import retrofit2.Call

class Repository : KoinComponent {

    fun addEmployee(employee: Employee): Call<Employee> {
        return RetrofitInstance.api.addEmployee(employee)
    }

    fun deleteEmployee(id: Int): Call<Void> {
        return RetrofitInstance.api.deleteEmployee(id)
    }

}