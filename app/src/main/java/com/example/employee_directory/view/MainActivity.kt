package com.example.employee_directory.view

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.employee_directory.R
import com.example.employee_directory.api.Api
import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.databinding.ActivityMainBinding
import com.example.employee_directory.model.Employee
import com.example.employee_directory.onBoard.CustomIntro
import com.example.employee_directory.repository.Repository
import com.example.employee_directory.utils.Constants.Companion.BASE_URL
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    var currentPage: Int = 0
    val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
            )

//        val callList: Call<List<Employee>> = RetrofitInstance.api.getData()
//
//        callList.enqueue(object : Callback<List<Employee>> {
//            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
//                println("eeeeeeeeeeeeeeeee----------->>>" + t)
//            }
//
//            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
////                println("---------------------->>>>>>" + response.body())
//            }
//        })
        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            var response = api.getData().awaitResponse()
            println("---------------------->>>>>>" + response.body())
        }

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
}