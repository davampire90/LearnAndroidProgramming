package com.example.myapplication

import java.sql.Time
import java.time.LocalDateTime
import java.time.LocalTime

data class Worker(
    val id: Int = 0,
    var name: String = "",
    var startWork: String,
    var stopWork: String,
    var pauseWork: Int,
    var formatDateTime: String
) {

    fun ConvertMinutes(a:Int,b:Int):Int{
        return (a*60+b)
    }
}