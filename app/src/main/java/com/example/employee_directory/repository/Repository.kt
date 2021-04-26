package com.example.employee_directory.repository

import com.example.employee_directory.R
import com.example.employee_directory.api.Api
import com.example.employee_directory.api.RetrofitInstance
import com.example.employee_directory.model.Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class Repository {
    suspend fun pushPost(data: Data) : Response<Data> {
        return RetrofitInstance.api.pushPost(data)
    }

    fun getData(): Call<List<Data>> {
        return RetrofitInstance.api.getData()
    }



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
}