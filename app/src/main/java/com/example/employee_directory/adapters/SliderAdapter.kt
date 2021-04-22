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

////ПЕРЕКИНУТЬ ИНФОРМАЦИЮ В РЕПОЗИТОРИЙ
class SliderAdapter(var context: Context) : PagerAdapter() {
    lateinit var layoutInflater: LayoutInflater

    var slide_description: Array<String> = arrayOf(
        "List представляет последовательный список элементов. List представляет неизменяемую (immutable) " +
                "коллекцию, которая в основном только обеспечивает получение элементов по позиции.",
        "Изменяемые списки представлены интерфейсом MutableList. Он расширяет интерфейс List и позволяют " +
                "добавлять и удалять элементы. Данный интерфейс реализуется классом ArrayList.",
        "Уже не первый год компании решают проблему под название «показатель оттока клиентов» или «Churn " +
                "Rate». За это время было протестировано огромное кол-во разных комбинаций «вводить в курс дела» пользователей. "
    )

    var slide_icon = listOf(
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_foreground,
        R.drawable.onboarding_pager_circle_icon
    )

    val slide_heading: Array<String> = arrayOf("One slide", "Two slide", "Free slide")


    override fun isViewFromObject(view: View, ob: Any): Boolean {
        return view == ob;
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.slide_layout, container, false)

        val slideIcon: ImageView = view.findViewById(R.id.slideIcon)
        val slideHeading: TextView = view.findViewById(R.id.slideHeading)
        val slideDescription: TextView = view.findViewById(R.id.slideDescription)

        slideIcon.setImageResource(slide_icon[position])
        slideHeading.text = slide_heading[position]
        slideDescription.text = slide_description[position]



        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, ob: Any) {
        container.removeView(ob as RelativeLayout)
    }

    override fun getCount(): Int {
        return slide_heading.count()
    }
}