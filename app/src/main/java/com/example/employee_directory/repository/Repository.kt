package com.example.employee_directory.repository

import androidx.lifecycle.MutableLiveData
import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.db.room.DaoEmployee
import com.example.employee_directory.model.Employee
import com.example.employee_directory.model.PostRequest
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository : KoinComponent {

    private val dao: DaoEmployee by inject()

    fun addEmployee(employee: Employee) : Call<Employee> {
        return RetrofitInstance.api.addEmployee(employee)
    }

    fun deleteEmployee(id: Int) : Call<Void> {
        return RetrofitInstance.api.deleteEmployee(id)
    }
}