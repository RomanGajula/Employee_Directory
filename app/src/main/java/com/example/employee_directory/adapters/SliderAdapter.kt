package com.example.employee_directory.adapters

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.employee_directory.R
import com.example.employee_directory.repository.Repository

////ПЕРЕКИНУТЬ ИНФОРМАЦИЮ В РЕПОЗИТОРИЙ
class SliderAdapter(var context: Context) : PagerAdapter() {
    lateinit var layoutInflater: LayoutInflater
    var repository = Repository()


    override fun isViewFromObject(view: View, ob: Any): Boolean {
        return view == ob;
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.slide_layout, container, false)

        val slideIcon: ImageView = view.findViewById(R.id.slideIcon)
        val slideHeading: TextView = view.findViewById(R.id.slideHeading)
        val slideDescription: TextView = view.findViewById(R.id.slideDescription)

        slideIcon.setImageResource(repository.slide_icon[position])
        slideHeading.text = repository.slide_heading[position]
        slideDescription.text = repository.slide_description[position]



        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, ob: Any) {
        container.removeView(ob as RelativeLayout)
    }

    override fun getCount(): Int {
        return repository.slide_heading.count()
    }
}