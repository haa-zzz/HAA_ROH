package com.example.haa_roh.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haa_roh.base.BaseViewModel

class DiaryViewModel : BaseViewModel(){

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}