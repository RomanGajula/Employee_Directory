package com.example.employee_directory.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employee_directory.model.Employee
import com.example.employee_directory.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class AddEmployeeViewModel : ViewModel(), KoinComponent {
    private val repository: Repository by inject()
    val name = MutableLiveData("")
    val age = MutableLiveData("")
    val salary = MutableLiveData("")

    fun clickCreateEmployee() {
        repository.addEmployee(
                Employee(
                        null,
                        name.value.toString(),
                        salary.value.toString(),
                        age.value.toString(),
                        null
                )
        )
        Log.d("Message: ", name.value.toString())

    }
}