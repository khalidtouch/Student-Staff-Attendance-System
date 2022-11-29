package com.andela.eduteam14.android_app.core.ui.extensions

import android.widget.Button
import androidx.cardview.widget.CardView


fun CardView.onClick(handler: () -> Unit) {
    setOnClickListener {
        handler()
    }
}