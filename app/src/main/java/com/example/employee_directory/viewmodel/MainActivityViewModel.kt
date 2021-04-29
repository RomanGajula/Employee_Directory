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
//    lateinit var deleteEmployeeLiveData: LiveData<Call<String>>
//    val adapter: StaffAdapter = StaffAdapter()

//    fun pushData(data: Data) {
//        viewModelScope.launch {
//            val response = repository.pushPost(data)
//            myResponse.value = response
//        }
//    }

//    fun getData () {
//        viewModelScope.launch {
//            val response = repository.getData()
//            getResponse.value = response
//        }
//    }

    fun deleteEmployee(id: Int) {
        repository.deleteEmployee(id)
    }

//    fun deleteHotel(employee: Employee) {
//        MainScope().launch {
//            withContext(Dispatchers.IO) {
//                repository.deleteHotel(employee)
//            }
//        }
//    }
}