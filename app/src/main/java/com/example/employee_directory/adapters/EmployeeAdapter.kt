package com.example.employee_directory.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.employee_directory.databinding.ItemEmployeeBinding
import com.example.employee_directory.model.Employee
import com.example.employee_directory.repository.Repository
import com.example.employee_directory.viewmodel.AddEmployeeViewModel
import com.example.employee_directory.viewmodel.MainActivityViewModel
import okhttp3.internal.notify
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeAdapter() : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>(), KoinComponent {

    //    private lateinit var dataList: MutableList<Data>
    val mainActivityViewModel: MainActivityViewModel by inject()
    val addEmployeeViewModel: AddEmployeeViewModel by inject()

    companion object {
        var employeesList: MutableList<Employee> = arrayListOf()
    }

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
//        holder.bind()
        val employeeList = EmployeeAdapter.employeesList[position]
        holder.binding.nameUser.text = employeeList.employeeName
        holder.binding.age.text = "age: ${employeeList.employeeAge}"
        holder.binding.salary.text = "salary: ${employeeList.employeeSalary}"

        holder.binding.buttonDelete.setOnClickListener {
            employeeList.let {
                addEmployeeViewModel.repository.deleteEmployee(it.id!!.toInt()).enqueue(object : Callback<Void> {
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        println("------------>>>>>" + t)
                    }

                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        println(EmployeeAdapter.employeesList)
                        EmployeeAdapter.employeesList.remove(it)
                        setData(EmployeeAdapter.employeesList)
                        println(EmployeeAdapter.employeesList)
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

//    fun addEmployee(employee: Employee) {
//        addEmployeeViewModel.
//    }
}