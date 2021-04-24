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


//        binding.addName.addTextChangedListener(object: TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (binding.addName.text.toString() == "") {
//                    binding.addName.error =
//                        "Enter the name of the staff!"
//                } else {
//                    binding.addName.error = null
//                }
//            }
//
//        })
    }

    fun clickCreateEmployee(view: View) {

        if (validate()) {
            val intent = Intent(this@AddEmployee, Home::class.java)
            startActivity(intent)
        } else {
            if (binding.addName.text.isEmpty()) {
                binding.addName.error = "Enter the name of the staff"
            } else if (binding.addAge.text.isEmpty() || binding.addName.text.length > 3) {
                binding.addAge.error = "Enter the employee's age"
            } else if (binding.addSalary.text.isEmpty()) {
                binding.addSalary.error = "Enter the employee's salary"
            }
        }

//        if (binding.addName.text.toString() == "" ||
//            binding.addAge.text.toString() == "" || binding.addSalary.text.toString() == ""
//        ) {
//            Toast.makeText(
//                applicationContext,
//                "Please fill in all the fields!",
//                Toast.LENGTH_LONG
//            ).show()
////            if (binding.addName.text.isNotEmpty() && binding.addAge.text.toString() == "") {
////                binding.addName.error = "Enter the employee's age!"
////            }
//            when ("") {
//                binding.addName.text.toString() -> binding.addName.error =
//                    "Enter the name of the staff!"
//                binding.addAge.text.toString() -> binding.addName.error =
//                    "Enter the employee's age!"
//                binding.addSalary.text.toString() -> binding.addName.error =
//                    "Enter the employee's salary!"
//            }
//        } else {
//            val intent = Intent(this@AddEmployee, Home::class.java)
//            startActivity(intent)
//        }
    }

    fun validate(): Boolean {
        if (binding.addName.text.isEmpty()) {
            return false
        } else if (binding.addAge.text.isEmpty()) {
            return false
        } else if (binding.addSalary.text.isEmpty()) {
            return false
        }
        return true
    }
}