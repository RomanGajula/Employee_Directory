package com.example.employee_directory.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.employee_directory.databinding.ItemEmployeeBinding
import com.example.employee_directory.model.Employee
import com.example.employee_directory.view.MainActivity
import com.example.employee_directory.view.UpdateEmployee
import com.example.employee_directory.viewmodel.AddEmployeeViewModel
import com.example.employee_directory.viewmodel.MainActivityViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeAdapter() : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>(), KoinComponent {

    val mainActivityViewModel: MainActivityViewModel by inject()
    val addEmployeeViewModel: AddEmployeeViewModel by inject()

    companion object {
        var employeesList: MutableList<Employee> = arrayListOf()
    }

    inner class MyViewHolder(val binding: ItemEmployeeBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val employee = employeesList[bindingAdapterPosition]
            binding.modelEmployee = employee
            val view = binding.root
            binding.buttonChange.setOnClickListener {
                val intent = Intent(view.context, UpdateEmployee::class.java)
                intent.putExtra("id", employee.id)
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemsView = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemsView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
        val employeeList = EmployeeAdapter.employeesList[position]
        holder.binding.nameUser.text = employeeList.employeeName
        holder.binding.age.text = "age: ${employeeList.employeeAge}"
        holder.binding.salary.text = "salary: ${employeeList.employeeSalary}"

        holder.binding.buttonDelete.setOnClickListener {
            employeeList.let {
                addEmployeeViewModel.repository.deleteEmployee(it.id!!.toInt()).enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        println(t)
                    }

                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        EmployeeAdapter.employeesList.remove(it)
                        setData(EmployeeAdapter.employeesList)
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int {
        return EmployeeAdapter.employeesList.size
    }

    fun setData(employeeList: MutableList<Employee>) {
        EmployeeAdapter.employeesList = employeeList
        notifyDataSetChanged()
    }
}
