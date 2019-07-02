package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val newFullName = fullName?.trim()
        val parts: List<String>? = newFullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        if (fullName.isNullOrBlank()|| fullName.isEmpty()||fullName==" ") {
            firstName = null
            lastName = null
        }
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        //TODO FIX ME!!!
        return "nn"
    }

    fun toinitials(firstName: String?, lastName: String?): String? {
        var firstChar   = firstName?.get(0)
        var lastChar = lastName?.get(0)
        var init: String? ="$firstChar$lastChar"
        var new = init?.toUpperCase()
        return new
    }
}