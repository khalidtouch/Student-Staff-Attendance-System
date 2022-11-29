package com.andela.eduteam14.android_app.core.ui.extensions

import android.widget.ImageView


fun ImageView.onClick(handler: () -> Unit) {
    setOnClickListener {
        handler()
    }
}

