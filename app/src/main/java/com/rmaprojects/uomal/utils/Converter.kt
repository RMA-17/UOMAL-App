package com.rmaprojects.uomal.utils

import java.text.SimpleDateFormat
import java.util.*

object Converter {
    fun dateConverter(day: Int, month: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val sdf = SimpleDateFormat("MM yyyy", Locale.getDefault()).format(Date(calendar.timeInMillis))
        val airedDate = sdf.split(" ")[0]
        val airedMonth = sdf.split(" ")[1]
        return "$airedDate to $airedMonth"
    }
}