package ru.skillbranch.devintensive.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.app.Activity as Activity

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);
}