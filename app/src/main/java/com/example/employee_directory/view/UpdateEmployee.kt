package com.example.employee_directory.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.employee_directory.R
import com.example.employee_directory.databinding.ActivityUpdateEmployeeBinding
import com.example.employee_directory.dialog.CancelDialog
import com.example.employee_directory.model.Employee
import com.example.employee_directory.viewmodel.UpdateEmployeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateEmployee : AppCompatActivity(), KoinComponent {

    private val updateEmployeeViewModel: UpdateEmployeeViewModel by viewModel()
    var idToUpdate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityUpdateEmployeeBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_update_employee
        )

        idToUpdate = intent.getStringExtra("id")

        updateEmployeeViewModel.getEmployeeById(idToUpdate?.toInt()!!).enqueue(object : Callback<List<Employee>> {
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                println(t)
            }

            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
                response.body()?.forEach {
                    binding.updateName.setText(it.employeeName)
                    binding.updateAge.setText(it.employeeAge)
                    binding.updateSalary.setText(it.employeeSalary)
                }
            }
        })

        binding.buttonUpdate.setOnClickListener {
            updateEmployeeViewModel.updateEmployee(
                    idToUpdate!!.toInt(),
                    Employee(
                            idToUpdate.toString(),
                            binding.updateName.text.toString(),
                            binding.updateSalary.text.toString(),
                            binding.updateAge.text.toString(),
                            null)
            ).enqueue(object : Callback<Employee> {
                override fun onFailure(call: Call<Employee>, t: Throwable) {
                    println(t)
                }

                override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                    val intent = Intent(this@UpdateEmployee, MainActivity::class.java)
                    startActivity(intent);
                }
            })
        }

        binding.apply {
            lifecycleOwner = this@UpdateEmployee
            updateEmployeeViewModel = this@UpdateEmployee.updateEmployeeViewModel
        }
    }

    fun clickCancel(view: View) {
        CancelDialog().show(supportFragmentManager, "Cancel")
    }
}