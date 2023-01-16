package com.rmaprojects.uomal.utils

import java.text.SimpleDateFormat
import java.util.*

object Converter {
    fun dateConverter(day: Int?, month: Int?, year: Int?): String {
        if (year == null || month == null || day == null) return "Unknown"

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val sdf = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(Date(calendar.timeInMillis))
        val airedMonth = sdf.split(" ")[0]
        val airedYear = sdf.split(" ")[1]
        return "$airedMonth $airedYear"
    }
}