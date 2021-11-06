package com.example.haa_roh.bean
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField

/**
 * plan页面采用 单/双向数据绑定的方式
 */
class PlanDataBingBean{
    val editText = ObservableField<String>()
    var tag = ObservableField<String>()
}

class  DailyData{
    val photo = ObservableField<String>()
    val time = ObservableField<String>()
    val week = ObservableField<String>()
    val weather = ObservableField<String>()
    val english = ObservableField<String>()
    val chinese = ObservableField<String>()
    val reportTitle = ObservableField<String>()
    //温度
    val temp = ObservableField<String>()
    //相对湿度
    val sd = ObservableField<String>()

    val pm25 = ObservableField<String>()
    //风级
    val ws = ObservableField<String>()

    val historyArray = ObservableArrayList<Da>()
}
