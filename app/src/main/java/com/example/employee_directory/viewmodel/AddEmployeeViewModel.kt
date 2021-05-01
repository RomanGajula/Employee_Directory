package com.example.employee_directory.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.employee_directory.model.Employee
import com.example.employee_directory.repository.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call

class AddEmployeeViewModel : ViewModel(), KoinComponent {
    val repository: Repository by inject()

    fun createEmployee(name: String, age: String, salary: String): Call<Employee> {
        return repository.addEmployee(
                Employee(
                        null,
                        name,
                        salary,
                        age,
                        null
                )
        )
        Log.d("Message", name)
    }
}