package com.example.employee_directory.repository

import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.db.room.DaoEmployee
import com.example.employee_directory.model.Employee
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Response

class Repository : KoinComponent {

    private val dao: DaoEmployee by inject()

    suspend fun pushPost(employee: Employee) : Response<Employee> {
        return RetrofitInstance.api.pushPost(employee)
    }

    fun getData(): Call<Employee> {
        return RetrofitInstance.api.getData()
    }

    suspend fun addHotel(employee: Employee) {
        dao.insert(employee)
    }

    suspend fun updateHotel(employee: Employee) {
        dao.update(employee)
    }

    suspend fun deleteHotel(employee: Employee) {
        dao.delete(employee)
    }

    fun getHotel() = dao.getAll()
}