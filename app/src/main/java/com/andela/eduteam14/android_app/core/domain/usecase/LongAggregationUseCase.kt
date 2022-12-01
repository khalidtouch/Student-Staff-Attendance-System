package com.andela.eduteam14.android_app.core.domain.usecase

import com.andela.eduteam14.android_app.core.data.models.CreateClassRequest

class LongAggregationUseCase {

    operator fun invoke(items: ArrayList<String>): String {
        var result: Long = 0L

        items.forEach {
            result += it.toLong()
        }

        return result.toString()
    }
}