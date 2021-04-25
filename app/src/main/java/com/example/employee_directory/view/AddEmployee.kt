package com.example.employee_directory.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.example.employee_directory.R
import com.example.employee_directory.databinding.ActivityAddEmployeeBinding
import com.example.employee_directory.viewmodel.AddEmployeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

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


        binding.addAge.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BUTTON_13) {
                //Perform Code
                println("22222222222222")
                return@OnKeyListener true
            }
            false
        })
    }

    fun clickCreateEmployee(view: View) {
        if (binding.addName.text.isEmpty()) {
            binding.addName.error = "Enter the name of the staff"
        } else if (binding.addAge.text.isEmpty()) {
            binding.addAge.error = "Enter the employee's age"
        } else if (binding.addAge.text.length > 3) {
            binding.addAge.error = "Enter your real age"
        } else if (binding.addSalary.text.isEmpty()) {
            binding.addSalary.error = "Enter the employee's salary"
        } else {
            val intent = Intent(this@AddEmployee, Home::class.java)
            startActivity(intent)
        }
    }
}