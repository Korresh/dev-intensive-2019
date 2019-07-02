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

    fun transliteration(payload: String, divider: String = " "): String{
        return "YY"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var initials: String?
        when {
            firstName.isNullOrBlank() && lastName.isNullOrBlank() -> initials = null
            firstName.isNullOrBlank() -> initials = lastName?.first().toString().toUpperCase()
            lastName.isNullOrBlank() -> initials = firstName?.first().toString().toUpperCase()
            else -> initials = (firstName?.first().toString()+lastName?.first().toString()).toUpperCase()
        }
        return (initials)
    }
}