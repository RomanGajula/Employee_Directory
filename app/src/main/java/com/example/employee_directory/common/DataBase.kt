package com.example.employee_directory.common

import androidx.room.RoomDatabase
import com.example.employee_directory.db.room.DaoEmployee

abstract class DataBase : RoomDatabase() {
    abstract val dao: DaoEmployee
}