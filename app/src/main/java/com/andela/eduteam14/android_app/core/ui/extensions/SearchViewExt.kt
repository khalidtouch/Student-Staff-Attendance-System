package com.andela.eduteam14.android_app.core.ui.extensions

import android.widget.SearchView


fun SearchView.onSearch(handler: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            handler(newText!!)
            return false
        }
    })
}

fun SearchView.onClick(handler: () -> Unit) {
    setOnClickListener { handler() }
}