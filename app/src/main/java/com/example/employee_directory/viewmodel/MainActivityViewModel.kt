package com.example.employee_directory.viewmodel

import retrofit2.Call
import androidx.lifecycle.ViewModel
import com.example.employee_directory.model.Employee
import com.example.employee_directory.repository.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivityViewModel() : ViewModel(), KoinComponent {
    val repository: Repository by inject()

    fun getEmployee(): Call<MutableList<Employee>> {
        return repository.getEmployee()
    }

    fun getEmployeeSearch(employee_name: String): Call<List<Employee>> {
        return repository.getEmployeeSearch(employee_name)
    }

}