package ru.skillbranch.devintensive.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.app.Activity as Activity

fun Activity.hideKeyboard() {
    val view = this.currentFocus
    view?.let { v ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.let { it.hideSoftInputFromWindow(v.windowToken, 0) }
    }
}