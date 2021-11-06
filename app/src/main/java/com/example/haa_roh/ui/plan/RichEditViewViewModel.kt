package com.example.haa_roh.ui.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.haa_roh.bean.Bmob.PlanAdapterBean
import com.example.haa_roh.bean.ResultData
import com.example.haa_roh.db.setPlanBMob

class RichEditViewViewModel : ViewModel() {
    private val _changedPlanLiveData = MutableLiveData<ResultData>()
    val changedPlanLiveData : LiveData<ResultData> = _changedPlanLiveData

    fun changePlan(id : String, planAdapterBean: PlanAdapterBean){
        setPlanBMob(id,planAdapterBean,_changedPlanLiveData)
    }
}