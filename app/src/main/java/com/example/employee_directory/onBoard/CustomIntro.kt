package com.example.employee_directory.onBoard

import android.content.Intent
import android.os.Bundle
import com.example.employee_directory.R
import com.example.employee_directory.view.MainActivity
import com.github.paolorotolo.appintro.AppIntro2


class CustomIntro : AppIntro2() {
    override fun init(savedInstanceState: Bundle?) {

        addSlide(SampleSlide.newInstance(R.layout.intro_1)) //
        addSlide(SampleSlide.newInstance(R.layout.intro_2))
        addSlide(SampleSlide.newInstance(R.layout.intro_3))
    }


    private fun loadMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onNextPressed() {
        // Do something here
    }

    override fun onDonePressed() {
        finish()
    }

    override fun onSlideChanged() {
        // Do something here
    }
}
