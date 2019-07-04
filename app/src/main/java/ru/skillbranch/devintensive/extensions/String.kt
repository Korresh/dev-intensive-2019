package ru.skillbranch.devintensive.extensions

fun String.truncate(i: Int=16): String {
    val add = "..."
    if (this.trim().length > i) {
        var newStr = this.trim().substring(0,i).trim()
        return "$newStr$add"
    } else {
        return "${this.trim()}"
    }
}