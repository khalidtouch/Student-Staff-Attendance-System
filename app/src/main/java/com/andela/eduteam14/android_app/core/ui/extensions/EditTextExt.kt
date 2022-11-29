package com.andela.eduteam14.android_app.core.ui.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener

fun EditText.onChange(handler: (String) -> Unit) {
    val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            handler(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {

        }

    }
    addTextChangedListener(watcher)

}