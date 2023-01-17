package com.rmaprojects.uomal.utils

import com.rmaprojects.uomal.data.remote.model.animelist.Prop
import java.text.SimpleDateFormat
import java.util.*
import com.rmaprojects.uomal.data.remote.model.anime.Prop as PropDetail

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

fun getAnimeAiringDate(prop: Prop) : String {
    val animeFrom = Converter.dateConverter(
        prop.from.day,
        prop.from.month,
        prop.from.year
    )
    val animeTo = Converter.dateConverter(
        prop.to.day,
        prop.to.month,
        prop.to.year
    )
    return "$animeFrom to $animeTo"
}fun getAnimeAiringDate(prop: PropDetail) : String {
    val animeFrom = Converter.dateConverter(
        prop.from.day,
        prop.from.month,
        prop.from.year
    )
    val animeTo = Converter.dateConverter(
        prop.to.day,
        prop.to.month,
        prop.to.year
    )
    return "$animeFrom to $animeTo"
}