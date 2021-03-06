package com.example.employee_directory.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.employee_directory.R
import com.example.employee_directory.adapters.EmployeeAdapter
import com.example.employee_directory.databinding.ActivityAddEmployeeBinding
import com.example.employee_directory.dialog.CancelDialog
import com.example.employee_directory.model.Employee
import com.example.employee_directory.viewmodel.AddEmployeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddEmployee : AppCompatActivity(), KoinComponent {

    private val addEmployeeViewModel: AddEmployeeViewModel by viewModel()
    lateinit var binding: ActivityAddEmployeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        binding =
                DataBindingUtil.setContentView(
                        this,
                        R.layout.activity_add_employee
                )
        binding.apply {
            lifecycleOwner = this@AddEmployee

        }

    }

    fun clickCancel(view: View) {
        CancelDialog().show(supportFragmentManager, "Cancel")
    }

    fun clickCreateEmployee(view: View) {
        when {
            binding.addName.text.isEmpty() -> {
                binding.addName.error = "Enter the name of the staff"
            }
            binding.addAge.text.isEmpty() -> {
                binding.addAge.error = "Enter the employee's age"
            }
            binding.addAge.text.length > 3 -> {
                binding.addAge.error = "Enter your real age"
            }
            binding.addSalary.text.isEmpty() -> {
                binding.addSalary.error = "Enter the employee's salary"
            }
            else -> {
                addEmployeeViewModel.createEmployee(binding.addName.text.toString(), binding.addAge.text.toString(), binding.addSalary.text.toString()).enqueue(object : Callback<Employee> {
                    override fun onFailure(call: Call<Employee>, t: Throwable) {
                        println(t)
                    }

                    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                        EmployeeAdapter.employeesList.add(response.body()!!)
                    }
                })
                val intent = Intent(this@AddEmployee, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}