package com.example.employee_directory.common.koin

import com.example.employee_directory.repository.Repository
import com.example.employee_directory.viewmodel.AddEmployeeViewModel
import com.example.employee_directory.viewmodel.MainActivityViewModel
import com.example.employee_directory.viewmodel.UpdateEmployeeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AddEmployeeViewModel() }
    viewModel { UpdateEmployeeViewModel() }
    viewModel { MainActivityViewModel(Repository()) }
    single { Repository() }
}