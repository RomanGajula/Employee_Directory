package com.example.employee_directory.viewmodel

import retrofit2.Call
import retrofit2.Response
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employee_directory.model.Employee
import com.example.employee_directory.repository.Repository

class MainActivityViewModel(var repository: Repository) : ViewModel() {
    var myResponse: MutableLiveData<Response<Employee>> = MutableLiveData()
    var getResponse: MutableLiveData<Call<Employee>> = MutableLiveData()

}