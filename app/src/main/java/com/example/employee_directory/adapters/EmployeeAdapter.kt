package com.example.employee_directory.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employee_directory.databinding.ItemEmployeeBinding
import com.example.employee_directory.model.Employee
import com.example.employee_directory.repository.Repository
import com.example.employee_directory.viewmodel.AddEmployeeViewModel
import com.example.employee_directory.viewmodel.MainActivityViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeAdapter() : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>(), KoinComponent {

    //    private lateinit var dataList: MutableList<Data>
    val mainActivityViewModel: MainActivityViewModel by inject()
    val addEmployeeViewModel: AddEmployeeViewModel by inject()
    var employeeList = emptyList<Employee>()
    private lateinit var context: Context

    inner class MyViewHolder(val binding: ItemEmployeeBinding) :
            RecyclerView.ViewHolder(binding.root) {
//        fun bind() {
//            val hotel = employeeList[bindingAdapterPosition]
//            binding.modelEmployee = hotel
//            val view = binding.root
//
//            binding.buttonDelete.setOnClickListener {
//                mainActivityViewModel.deleteEmployee(hotel.id!!.toInt())
//                this@EmployeeAdapter.notifyDataSetChanged()
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemsView = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemsView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind()
        val employeeList = employeeList[position]
        holder.binding.nameUser.text = employeeList.employeeName
        holder.binding.age.text = "age: ${employeeList.employeeAge}"
        holder.binding.salary.text = "salary: ${employeeList.employeeSalary}"

        holder.binding.buttonDelete.setOnClickListener {
            employeeList.let {
                addEmployeeViewModel.repository.deleteEmployee(it.id!!.toInt()).enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        println("------------>>>>>" + t)
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        println("-------------->>> response " + response)
                        println("-------------->>> status " + response.body())
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    fun setData(employeeList: List<Employee>) {
        this.employeeList = employeeList
        notifyDataSetChanged()
    }

//    fun addEmployee(employee: Employee) {
//        addEmployeeViewModel.
//    }
}