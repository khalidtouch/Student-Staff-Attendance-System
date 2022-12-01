package com.andela.eduteam14.android_app.core.domain.usecase

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.andela.eduteam14.android_app.core.data.models.CreateClassRequest
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository

class StudentAggregationUseCase(
) {

    operator fun invoke(items: ArrayList<CreateClassRequest>): Pair<Long, Long> {
        var males: Long = 0L
        var females: Long = 0L

        items.forEach {
            males += it.numberOfMales.toLong()
            females += it.numberOfFemales.toLong()
        }

        return Pair<Long, Long>(males, females)
    }
}

