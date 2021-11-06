package com.example.haa_roh.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haa_roh.base.BaseViewModel
import com.example.haa_roh.bean.DailyData
import com.example.haa_roh.repository.retrofitPostImage
import com.example.haa_roh.repository.retrofitPostLiShIjr
import com.example.haa_roh.repository.retrofitPostWeather

class DiaryViewModel : BaseViewModel(){

    //https://v0.yiketianqi.com/api?version=v61&appid=53956445&appsecret=0E8nCSd1
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text


    private val _diaryLiveData = MutableLiveData<DailyData>()
    val diaryLiveData : LiveData<DailyData> = _diaryLiveData

    fun getAllData(){
        retrofitPostImage(_diaryLiveData)
        retrofitPostWeather(_diaryLiveData)
        retrofitPostLiShIjr(_diaryLiveData)
    }
}