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

//    companion object{
//        var employeesList: MutableList<Employee> = arrayListOf()
//    }


    private val dao: DaoEmployee by inject()

    fun addEmployee(employee: Employee) : Call<Employee> {
        return RetrofitInstance.api.addEmployee(employee)
    }

    fun deleteEmployee(id: Int) : Call<Void> {
        return RetrofitInstance.api.deleteEmployee(id)
    }

//    fun getData(): Call<Employee> {
//        return RetrofitInstance.api.getData()
//    }







//    fun deleteEmployee(id: Int): LiveData<Boolean> {
//        val data = MutableLiveData<Boolean>()
//
//        RetrofitInstance.api?.deleteEmployee(id)?.enqueue(object : Callback<String> {
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                data.value = false
//            }
//
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                data.value = response.code() == 200
//            }
//        })
//        return data
//    }


//    suspend fun addHotel(employee: Employee) {
//        dao.insert(employee)
//    }
//
//    suspend fun updateHotel(employee: Employee) {
//        dao.update(employee)
//    }
//
//    suspend fun deleteHotel(employee: Employee) {
//        dao.delete(employee)
//    }
//
//    fun getHotel() = dao.getAll()
}