package com.example.employee_directory.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.employee_directory.model.Data

interface DaoEmployee {

//    @Query("SELECT * FROM data")
    fun getAll(): LiveData<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg data: Data)

    @Delete
    suspend fun delete(vararg data: Data)

    @Update
    suspend fun update(data: Data)
}