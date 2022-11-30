package com.andela.eduteam14.android_app.core.ui.extensions

import android.text.Editable

fun String.long(): Long {
    return this.toLong()
}

fun Editable.long(): Long {
    return toString().toLong()
}