package com.example.employee_directory.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employee_directory.R
import com.example.employee_directory.adapters.EmployeeAdapter
import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.databinding.ActivityMainBinding
import com.example.employee_directory.model.Employee
import com.example.employee_directory.onBoard.CustomIntro
import com.example.employee_directory.repository.Repository
import com.example.employee_directory.viewmodel.MainActivityViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), KoinComponent {
    val employeeAdapter by lazy { EmployeeAdapter() }
    val mainActivityViewModel: MainActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(
                        this,
                        R.layout.activity_main
                )

        val thread = Thread(Runnable {
            run {
                val getPrefs = PreferenceManager.getDefaultSharedPreferences(baseContext)
                val isFirstStart = getPrefs.getBoolean("FIRST_START", true)
                if (isFirstStart) {
                    val intent = Intent(this@MainActivity, CustomIntro::class.java)
                    startActivity(intent);

                    val e = getPrefs.edit();
                    e.putBoolean("FIRST_START", false);
                    e.apply();
                }
            }
        })
        thread.start()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = employeeAdapter
        }

        val callList: Call<MutableList<Employee>> = mainActivityViewModel.getEmployee()
        callList.enqueue(object : Callback<MutableList<Employee>> {
            override fun onFailure(call: Call<MutableList<Employee>>, t: Throwable) {
                println(t)
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onResponse(call: Call<MutableList<Employee>>, response: Response<MutableList<Employee>>) {
                val employee = response.body()
                employee?.let { employeeAdapter.setData(it) }
                if (EmployeeAdapter.employeesList.isNotEmpty()) {
                    binding.progressBar.visibility = View.INVISIBLE
                }
                if (employee!!.isEmpty()) {
                    Toast.makeText(applicationContext, "No data available!", Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.INVISIBLE
                }
            }
        })

        if (hasConnection(this)) {
            binding.message.visibility = View.INVISIBLE
        } else {
            binding.message.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar, menu)
        val searchManeger = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val search = menu?.findItem(R.id.search)?.actionView as SearchView

        search.setSearchableInfo(searchManeger.getSearchableInfo(componentName))
        search.queryHint = "Enter name employee"
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(qString: String): Boolean {
                if (qString.length > 2) {
                    getEmployeeSearch(qString)
                }
                return false
            }

            override fun onQueryTextSubmit(qString: String): Boolean {
                getEmployeeSearch(qString)
                return false
            }
        })

        search.setOnCloseListener {
            val callList: Call<MutableList<Employee>> = RetrofitInstance.api.getEmployee()

            callList.enqueue(object : Callback<MutableList<Employee>> {
                override fun onFailure(call: Call<MutableList<Employee>>, t: Throwable) {
                    println(t)
                }

                @RequiresApi(Build.VERSION_CODES.R)
                override fun onResponse(call: Call<MutableList<Employee>>, response: Response<MutableList<Employee>>) {
                    val employee = response.body()
                    employee?.let { employeeAdapter.setData(it) }
                }
            })
            false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.update -> clickUpdateActivity()
            R.id.add -> clickAddEmployee()
        }
        return super.onOptionsItemSelected(item)
    }

    fun clickAddEmployee() {
        val intent = Intent(this, AddEmployee::class.java)
        startActivity(intent)
    }

    fun clickUpdateActivity() {
        val callList: Call<MutableList<Employee>> = RetrofitInstance.api.getEmployee()
        callList.enqueue(object : Callback<MutableList<Employee>> {
            override fun onFailure(call: Call<MutableList<Employee>>, t: Throwable) {
                println(t)
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onResponse(call: Call<MutableList<Employee>>, response: Response<MutableList<Employee>>) {
                val employee = response.body()
                employee?.let { employeeAdapter.setData(it) }
                Toast.makeText(applicationContext, "List update!", Toast.LENGTH_LONG).show()
                if (employee!!.isEmpty()) {
                    Toast.makeText(applicationContext, "No data available!", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    fun getEmployeeSearch(string: String) {
        mainActivityViewModel.getEmployeeSearch(string).enqueue(object : Callback<List<Employee>> {
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                println(t)
            }

            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
                response.body()?.forEach {
                    val employee = response.body()
                    employee?.let { employeeAdapter.setData(it.toMutableList()) }
                }
            }
        })
    }

    fun hasConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifiInfo != null && wifiInfo.isConnected) {
            return true
        }
        wifiInfo = cm.activeNetworkInfo
        return wifiInfo != null && wifiInfo.isConnected
    }
}