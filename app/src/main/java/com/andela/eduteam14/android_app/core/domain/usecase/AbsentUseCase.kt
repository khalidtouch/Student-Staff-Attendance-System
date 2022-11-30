package com.andela.eduteam14.android_app.core.domain.usecase

class AbsentUseCase {
    operator fun invoke(present: Long, total: Long): Long {
        return total - present
    }
}