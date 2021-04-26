package com.example.employee_directory.onBoard

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.employee_directory.R
import com.example.employee_directory.adapters.SliderAdapter
import com.example.employee_directory.databinding.ActivityMainBinding
import com.example.employee_directory.view.Home

class MainActivity : AppCompatActivity() {
    var currentPage: Int = 0
    var sliderAdapter = SliderAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this,
                        R.layout.activity_main
                )

        //  Declare a new thread to do a preference check
        var thread = Thread(Runnable {
            run {
                var getPrefs = PreferenceManager.getDefaultSharedPreferences(baseContext)
                val isFirstStart = getPrefs.getBoolean("key", true)
                if (isFirstStart) {
                    var intent = Intent(this@MainActivity, CustomIntro::class.java)
                    startActivity(intent);

                    var e = getPrefs.edit();
                    e.putBoolean("FIRST_START", false);
                    e.apply();
                }
            }
        })
        thread.start()
    }


//        binding.slideViewPage.adapter = sliderAdapter
//        binding.slideViewPage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//
//            override fun onPageScrollStateChanged(state: Int) {
//            }
//
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//
//            }
//
//            override fun onPageSelected(position: Int) {
//                currentPage = position
//                if (position == 0) {
//                    binding.arrowPrev.isEnabled = false
//                    binding.arrowPrev.visibility = View.INVISIBLE
//
//                }
//                else if (position >= 0) {
//                    binding.arrowPrev.isEnabled = true
//                    binding.arrowPrev.visibility = View.VISIBLE
//                    binding.buttonStart.visibility = View.INVISIBLE
//                    binding.arrowNext.isEnabled = true
//                    binding.arrowNext.visibility = View.VISIBLE
//                }
//                if (position == (SliderAdapter(this@MainActivity).repository.slide_heading.size - 1)) {
//                    binding.arrowNext.isEnabled = false
//                    binding.arrowNext.visibility = View.INVISIBLE
//                    binding.buttonStart.visibility = View.VISIBLE
//
//                }
//            }
//        })


//    fun clickStart(view: View) {
//        val intent = Intent(this@MainActivity, Home::class.java)
//        startActivity(intent)
//    }
}