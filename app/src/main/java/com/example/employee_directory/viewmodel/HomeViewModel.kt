package com.example.employee_directory.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employee_directory.adapters.StaffAdapter
import com.example.employee_directory.model.Data
import com.example.employee_directory.repository.Repository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

class HomeViewModel(private  val repository: Repository) : ViewModel() {
    var myResponse: MutableLiveData<Response<Data>> = MutableLiveData()
    var getResponse: MutableLiveData<Response<Data>> = MutableLiveData()
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
