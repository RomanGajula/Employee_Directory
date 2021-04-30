package com.example.employee_directory.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employee_directory.model.Employee
import com.example.employee_directory.model.PostRequest
import com.example.employee_directory.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class AddEmployeeViewModel : ViewModel(), KoinComponent {
    val repository: Repository by inject()
    val name = MutableLiveData("")
    val age = MutableLiveData("")
    val salary = MutableLiveData("")
    lateinit var res: Call<Employee>


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