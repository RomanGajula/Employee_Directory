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
import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.databinding.ActivityMainBinding
import com.example.employee_directory.model.RequestData
import com.example.employee_directory.onBoard.CustomIntro
import com.example.employee_directory.repository.Repository
import retrofit2.*


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

        val callList: Call<RequestData> = RetrofitInstance.api.gettttEmployee()
//
        callList.enqueue(object : Callback<RequestData> {
            override fun onFailure(call: Call<RequestData>, t: Throwable) {
                println("eeeeeeeeeeeeeeeee----------->>>" + t)
            }

            override fun onResponse(call: Call<RequestData>, response: Response<RequestData>) {
                val employee = response.body()?.data
            }
        })

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