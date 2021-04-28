package com.example.employee_directory.common.koin

import com.example.employee_directory.common.DataBase
import com.example.employee_directory.repository.Repository
import com.example.employee_directory.viewmodel.AddEmployeeViewModel
import com.example.employee_directory.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AddEmployeeViewModel() }
    viewModel { MainActivityViewModel(Repository()) }
    single { Repository() }
    single { get<DataBase>().dao }
}