package com.example.employee_directory.adapters

import android.content.Context
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.employee_directory.R
import com.example.employee_directory.databinding.ItemStaffBinding
import com.example.employee_directory.db.DataList
import com.example.employee_directory.model.Data

class StaffAdapter() : RecyclerView.Adapter<StaffAdapter.MyViewHolder>() {

//    private lateinit var dataList: MutableList<Data>
    lateinit var dataList: DataList
    private lateinit var context: Context

    inner class MyViewHolder(val binding: ItemStaffBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemsView = ItemStaffBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemsView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val staffName = dataList.staff[position]
        holder.binding.nameUser.text = staffName.employeeName

//        val userName = holder.binding.nameUser
//        val userAge = holder.binding.age
//        val userSalary = holder.binding.salary
//        val fullName = "${data.employeeName}"
//        userName.text = fullName
    }

    override fun getItemCount(): Int {
        return dataList.staff.size
    }
}