package com.andela.eduteam14.android_app.core.domain.usecase

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

class DateTodayUseCase {

    @SuppressLint("SimpleDateFormat")
    operator fun invoke(): String {
        val today = Calendar.getInstance().time

        val format =
            SimpleDateFormat("MM dd, yyyy")

        val formatted = format.format(today)

        val monthNaming = MonthNamingUseCase()

        return "${monthNaming(formatted)} ${formatted.substring(2)}"
    }
}