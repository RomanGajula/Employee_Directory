package com.example.employee_directory.db.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.employee_directory.model.Employee

interface DaoEmployee {

//    @Query("SELECT * FROM data")
    fun getAll(): LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg data: Employee)

    @Delete
    suspend fun delete(vararg data: Employee)

    @Update
    suspend fun update(employee: Employee)
}