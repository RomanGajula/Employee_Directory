package com.example.employee_directory.viewmodel

import retrofit2.Call
import retrofit2.Response
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employee_directory.model.Data
import com.example.employee_directory.repository.Repository
import kotlinx.coroutines.launch

class MainActivityViewModel(var repository: Repository) : ViewModel() {
    var myResponse: MutableLiveData<Response<Data>> = MutableLiveData()
    var getResponse: MutableLiveData<Call<List<Data>>> = MutableLiveData()
//    val adapter: StaffAdapter = StaffAdapter()

    fun pushData(data: Data) {
        viewModelScope.launch {
            val response = repository.pushPost(data)
            myResponse.value = response
        }
    }

    fun getData () {
        viewModelScope.launch {
            val response = repository.getData()
            getResponse.value = response
        }
    }
}