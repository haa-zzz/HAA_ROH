package com.example.haa_roh.ui.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haa_roh.base.BaseViewModel
import com.example.haa_roh.bean.Bmob.PlanAdapterBean
import com.example.haa_roh.bean.ResultData
import com.example.haa_roh.bean.room.PlanRoom
import com.example.haa_roh.db.PHONEMES
import com.example.haa_roh.db.addPlanToBMob
import com.example.haa_roh.db.queryPlanBMob
import com.example.haa_roh.db.querySpNumber
import com.example.haa_roh.db.room.queryPlanFromRoom

class PlanModel : BaseViewModel() {


    private val _addPlanLiveData = MutableLiveData<ResultData>()
    val addPlanLiveData : LiveData<ResultData> = _addPlanLiveData

    private lateinit var queryPlanLiveData : LiveData<List<PlanRoom>>

    //向未完成的里面添加新计划
    fun addToBeCompleted(title: String, tag: String) {

        val planBean = PlanAdapterBean(
            title = title, tag = tag, isFinish = false, phone = PHONEMES ?: querySpNumber(),
            content = ""
        )
        addPlanToBMob(planBean,_addPlanLiveData)
    }
    fun queryPlan(){
        queryPlanLiveData = queryPlanFromRoom() ?: return
    }
    fun queryPlanFromBMob(){
        val phone = PHONEMES ?: querySpNumber()?:return
        queryPlanBMob(phone)
    }

    fun getQueryPlanLiveData() : LiveData<List<PlanRoom>>{
        return queryPlanLiveData
    }

    fun getAllTag() : Array<String> {
        //获取所有的标签
        return arrayOf("Android", "Kotlin", "Java")
    }
}