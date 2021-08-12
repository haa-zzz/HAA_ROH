package com.example.haa_roh.bean
import androidx.databinding.ObservableField

/**
 * plan页面采用 单/双向数据绑定的方式
 */
class PlanDataBingBean{
    val editText = ObservableField<String>()
    var tag = ObservableField<String>()
}
