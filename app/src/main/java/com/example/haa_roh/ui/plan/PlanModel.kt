package com.example.haa_roh.ui.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haa_roh.base.BaseViewModel

class PlanModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}