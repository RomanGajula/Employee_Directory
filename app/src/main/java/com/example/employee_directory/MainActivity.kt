package com.example.employee_directory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.employee_directory.adapters.SliderAdapter
import com.example.employee_directory.databinding.ActivityMainBinding
import com.example.employee_directory.viewmodel.HomeViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var currentPage: Int = 0
    var sliderAdapter = SliderAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.slideViewPage.adapter = sliderAdapter
        binding.slideViewPage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                currentPage = position
                if (position == 0) {
                    binding.arrowPrev.isEnabled = false
                    binding.arrowPrev.visibility = View.INVISIBLE
                }
                else if (position >= 0) {
                    binding.arrowPrev.isEnabled = true
                    binding.arrowPrev.visibility = View.VISIBLE
                    binding.buttonStart.visibility = View.INVISIBLE
                    binding.arrowNext.isEnabled = true
                    binding.arrowNext.visibility = View.VISIBLE
                }
                if (position == (SliderAdapter(this@MainActivity).slide_heading.size - 1)) {
                    binding.arrowNext.isEnabled = false
                    binding.arrowNext.visibility = View.INVISIBLE
                    binding.buttonStart.visibility = View.VISIBLE

                }
            }
        })
    }

    fun clickStart(view: View) {
        val intent = Intent(this@MainActivity, Home::class.java)
        startActivity(intent)
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
}