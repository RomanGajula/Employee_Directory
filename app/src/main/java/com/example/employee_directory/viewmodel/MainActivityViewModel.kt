package com.example.employee_directory.viewmodel

import retrofit2.Call
import retrofit2.Response
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employee_directory.model.Employee
import com.example.employee_directory.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(var repository: Repository) : ViewModel() {
    var myResponse: MutableLiveData<Response<Employee>> = MutableLiveData()
    var getResponse: MutableLiveData<Call<Employee>> = MutableLiveData()
//    val adapter: StaffAdapter = StaffAdapter()

//    fun pushData(data: Data) {
//        viewModelScope.launch {
//            val response = repository.pushPost(data)
//            myResponse.value = response
//        }
//    }

    fun getData () {
        viewModelScope.launch {
            val response = repository.getData()
            getResponse.value = response
        }
    }

    fun deleteHotel(employee: Employee) {
        MainScope().launch {
            withContext(Dispatchers.IO) {
                repository.deleteHotel(employee)
            }
        }
    }
}