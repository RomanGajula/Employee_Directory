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
import com.example.employee_directory.model.Data
import com.example.employee_directory.repository.Repository
import com.example.employee_directory.viewmodel.HomeViewModel
import com.example.employee_directory.viewmodel.MainViewModelFactory
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

//        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
//        homeViewModel.getData()
//        homeViewModel.getResponse.observe(this, Observer { response ->
//            println("----------------->>>" + response.isCanceled)
//        }

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
