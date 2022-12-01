package com.andela.eduteam14.android_app.core.domain.usecase

class MonthNamingUseCase {

    operator fun invoke(today: String): String {
        val month = today.substring(0, 2)
        return nameMonth(month)
    }


    private fun nameMonth(month: String): String {
        return when (month) {
            "01" -> "Jan"
            "02" -> "Feb"
            "03" -> "Mar"
            "04" -> "Apr"
            "05" -> "May"
            "06" -> "Jun"
            "07" -> "Jul"
            "08" -> "Aug"
            "09" -> "Sep"
            "10" -> "Oct"
            "11" -> "Nov"
            "12" -> "Dec"
            else -> "--"
        }
    }
}