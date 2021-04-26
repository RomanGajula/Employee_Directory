package com.example.employee_directory.db

import androidx.room.RoomDatabase

abstract class DataBase : RoomDatabase() {
    abstract val dao: DaoEmployee
}