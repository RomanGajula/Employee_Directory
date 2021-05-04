package com.example.employee_directory.repository

import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.model.Employee
import org.koin.core.KoinComponent
import retrofit2.Call

class Repository : KoinComponent {

    fun getEmployee(): Call<MutableList<Employee>> {
        return RetrofitInstance.api.getEmployee()
    }

    fun addEmployee(employee: Employee): Call<Employee> {
        return RetrofitInstance.api.addEmployee(employee)
    }

    fun updateEmployee(id: Int, employee: Employee): Call<Employee> {
        return RetrofitInstance.api.updateEmployee(id, employee)
    }

    fun getEmployeeById(id: Int): Call<List<Employee>> {
        return RetrofitInstance.api.getEmployeeById(id)
    }

    fun getEmployeeSearch(employee_name: String): Call<List<Employee>> {
        return RetrofitInstance.api.getEmployeeSearch(employee_name)
    }

    fun deleteEmployee(id: Int): Call<Void> {
        return RetrofitInstance.api.deleteEmployee(id)
    }

}