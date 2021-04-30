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

class AddEmployeeViewModel : ViewModel(), KoinComponent {
    val repository: Repository by inject()
    val name = MutableLiveData("")
    val age = MutableLiveData("")
    val salary = MutableLiveData("")


    fun createEmployee() : Call<Employee> {
//        viewModelScope.launch(Dispatchers.IO) {
            return repository.addEmployee(
                    Employee(
                    null,
                    name.value.toString(),
                    salary.value.toString(),
                    age.value.toString(),
                    null
            )
            )
//        }
        Log.d("Message", name.value.toString())
        Log.d("Message", age.value.toString())
        Log.d("Message", salary.value.toString())
    }

//    suspend fun addEmployee() {
//
//            Employee(
//                    null,
//                    name.value.toString(),
//                    salary.value.toString(),
//                    age.value.toString(),
//                    null
//            )
//    }


//    fun clickCreateEmployee() {
//        repository.addEmployee(
//                Employee(
//                        null,
//                        name.value.toString(),
//                        salary.value.toString(),
//                        age.value.toString(),
//                        null
//                )
//        )
//    }
}