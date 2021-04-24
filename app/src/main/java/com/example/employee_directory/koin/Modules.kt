package com.example.employee_directory.koin

import com.example.employee_directory.repository.Repository
import com.example.employee_directory.viewmodel.AddEmployeeViewModel
import com.example.employee_directory.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(Repository()) }
    viewModel { AddEmployeeViewModel() }
}