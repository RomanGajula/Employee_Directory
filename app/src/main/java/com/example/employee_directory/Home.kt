package com.example.employee_directory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.employee_directory.adapters.StaffAdapter
import com.example.employee_directory.databinding.ActivityHomeBinding
import com.example.employee_directory.databinding.ActivityMainBinding
import com.example.employee_directory.db.DataList
import com.example.employee_directory.model.Data
import com.example.employee_directory.model.Request
import com.example.employee_directory.repository.Repository
import com.example.employee_directory.viewmodel.HomeViewModel
import com.example.employee_directory.viewmodel.MainViewModelFactory
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException
import kotlin.reflect.KProperty

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
            DataBindingUtil.setContentView(this, R.layout.activity_home)

        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        homeViewModel.getData()
        homeViewModel.getResponse.observe(this, Observer { response ->
            println("----------------->>>" + response.body())
        })

//        binding.apply {
//            recyclerView.layoutManager = LinearLayoutManager(this@Home)
//            recyclerView.setHasFixedSize(true)
//            recyclerView.adapter = homeViewModel.adapter
//        }

//        AndroidNetworking.initialize(this)
//        AndroidNetworking.get("http://dummy.restapiexample.com/api/v1/employees").build().getAsObject(Request::class.java, object : ParsedRequestListener<Request> {
//            override fun onResponse(response: Request?) {
//                dataList.addAll(response!!.data)
//                homeViewModel.adapter.notifyDataSetChanged()
//            }
//
//            override fun onError(anError: ANError?) {}
//
//        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.update -> Toast.makeText(this, "Select Update", Toast.LENGTH_SHORT).show()
            R.id.add -> Toast.makeText(this, "Select Update", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

//    fun fetchJson() {
//        val url = "http://dummy.restapiexample.com/api/v1/employees"
//
//        val request = okhttp3.Request.Builder().url(url).build()
//        val client = OkHttpClient()
//        client.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call, response: Response) {
//                val body = response.body?.string()
//                println(body)
//
//                val gson = GsonBuilder().create()
//                val data = gson.fromJson(body, DataList::class.java)
//                StaffAdapter().dataList = data
//            }
//            override fun onFailure(call: Call, e: IOException) {
//                println("nooooooooooooooooooooooo")
//            }
//        })
//    }
}
