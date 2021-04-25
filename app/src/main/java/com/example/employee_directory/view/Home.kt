package com.example.employee_directory.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.employee_directory.R
import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.databinding.ActivityHomeBinding
import com.example.employee_directory.db.DataList
import com.example.employee_directory.model.Data
import com.example.employee_directory.repository.Repository
import com.example.employee_directory.viewmodel.HomeViewModel
import com.example.employee_directory.viewmodel.MainViewModelFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class Home : AppCompatActivity(), KoinComponent {
    // ДОБАВИТЬ INJECT
    private lateinit var homeViewModel: HomeViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)

    val dataList: MutableList<Data> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val binding: ActivityHomeBinding =
            DataBindingUtil.setContentView(this,
                R.layout.activity_home
            )

        val callList: Call<List<Data>> = RetrofitInstance.api.getData()

        callList.enqueue(object : Callback<List<Data>> {
            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                println("eeeeeeeeeeeeeeeee----------->>>" + t)
            }

            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                println("---------------------->>>>>>" + response.body())
            }
        })


//        binding.apply {
//            recyclerView.layoutManager = LinearLayoutManager(this@Home)
//            recyclerView.setHasFixedSize(true)
//            recyclerView.adapter = homeViewModel.adapter
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.update -> Toast.makeText(this, "Select Update", Toast.LENGTH_SHORT).show()
            R.id.add -> clickAddEmployee()
        }
        return super.onOptionsItemSelected(item)
    }

    fun clickAddEmployee() {
        val intent = Intent(this, AddEmployee::class.java)
        startActivity(intent)
    }

//    fun fetchJson() {
//        val url = "http://dummy.restapiexample.com/api/v1/employees"
//
//        val request = Request.Builder().url(url).build()
//        val client = OkHttpClient()
//        client.newCall(request).enqueue(object : Callback<Data>, okhttp3.Callback {
//            override fun onFailure(call: okhttp3.Call, e: IOException) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
//                val body = response.body?.toString()
//                println("1.-----------------" + body)
//
//                val gson = GsonBuilder().create()
//                val data = gson.fromJson(body, DataList::class.java)
//            }
//
//            override fun onFailure(call: Call<Data>, t: Throwable) {
//                println("--------->>>>" + t)
//            }
//
//            override fun onResponse(call: Call<Data>, response: Response<Data>) {
//                val body = response.body()?.toString()
//                println("2.-----------------" + body)
//
//                val gson = GsonBuilder().create()
//                val data = gson.fromJson(body, DataList::class.java)
//            }
//
//        })
//    }
}
