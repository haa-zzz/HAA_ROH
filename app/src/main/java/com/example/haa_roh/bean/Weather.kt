package com.example.haa_roh.bean

data class Weather(
    val code: String,
    val `data`: Data,
    val msg: String
)
data class Data(
    val SD: String,
    val WD: String,
    val WS: String,
    val cityname: String,
    val limitnumber: String,
    val nameen: String,
    val pm25: String,
    val temp: String,
    val time: String,
    val weather: String,
    val wse: String
)