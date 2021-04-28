package com.example.employee_directory.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employee_directory.databinding.ItemEmployeeBinding
import com.example.employee_directory.db.DataList
import com.example.employee_directory.model.Employee

class EmployeeAdapter() : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {

//    private lateinit var dataList: MutableList<Data>
    var employeeList = emptyList<Employee>()
    private lateinit var context: Context

    inner class MyViewHolder(val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemsView = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemsView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val employeeName = employeeList[position]
        holder.binding.nameUser.text = employeeName.employeeName
        holder.binding.age.text = "age: ${employeeName.employeeAge}"
                holder.binding.salary.text = "salary: ${employeeName.employeeSalary}"
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    fun setData(employeeList: List<Employee>) {
        this.employeeList = employeeList
        notifyDataSetChanged()
    }

}