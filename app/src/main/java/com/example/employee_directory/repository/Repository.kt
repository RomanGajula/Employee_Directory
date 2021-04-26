package com.example.employee_directory.repository

import com.example.employee_directory.R
import com.example.employee_directory.api.Api
import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.db.DaoEmployee
import com.example.employee_directory.model.Data
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class Repository : KoinComponent {

    private val dao: DaoEmployee by inject()

    suspend fun pushPost(data: Data) : Response<Data> {
        return RetrofitInstance.api.pushPost(data)
    }

    fun getData(): Call<List<Data>> {
        return RetrofitInstance.api.getData()
    }

    suspend fun addHotel(data: Data) {
        dao.insert(data)
    }

    suspend fun updateHotel(data: Data) {
        dao.update(data)
    }

    suspend fun deleteHotel(data: Data) {
        dao.delete(data)
    }

    fun getHotel() = dao.getAll()
}