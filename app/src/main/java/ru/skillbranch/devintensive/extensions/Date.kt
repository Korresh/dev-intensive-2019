package ru.skillbranch.devintensive.extensions



import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateformat = SimpleDateFormat(pattern, Locale("ru"))
    return dateformat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time  = this.time

    time +=when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY ->  value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val diff = abs(date.time - this.time)
    val seconds=diff/1000
    val minutes = diff/60000
    val hours = diff/3600000
    val days = diff/86000400
    val bolee = date.time > this.time
    println("diff: $diff ,seconds: $seconds ,minutes: $minutes ,hour: $hours ,days: $days")
    return if(bolee) when {
        days > 360 -> "более года назад"
        hours > 26 -> "$days  назад"
        hours in 23..26 -> "день назад"
        hours<=22&& minutes>75 ->"$hours назад"
        minutes in 46..75 ->"час назад"
        minutes<=45&& seconds>75 ->"$minutes назад"
        seconds in 46..75 ->"минуту назад"
        seconds in 2..45 ->"несколько секунд назад"
        else ->"только что"
    }else when{
        days >360 -> "более чем через год"
        hours > 26 -> "через $days дней"
        hours in 23..26 -> "через день"
        hours<=22&& minutes>75 ->"через $hours часа}"
        minutes in 46..75 ->"через час"
        minutes<=45&& seconds>75 ->"через $minutes минут}"
        seconds in 46..75 ->"через минуту"
        seconds in 2..45 ->"через несколько секунд"
        else ->"только что"
    }

}



enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
