package com.example.employee_directory.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employee_directory.model.Employee
import com.example.employee_directory.repository.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call

class UpdateEmployeeViewModel : ViewModel(), KoinComponent {
    val repository: Repository by inject()

    var name = MutableLiveData("")
    var age = MutableLiveData("")
    var salary = MutableLiveData("")

    fun getEmployeeById(id: Int): Call<List<Employee>> {
        return repository.getEmployeeById(id)
    }
}