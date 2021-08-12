package com.example.haa_roh.db.room

import androidx.lifecycle.LiveData
import com.example.haa_roh.base.BaseApplication
import com.example.haa_roh.bean.room.PersonalInformation
import com.example.haa_roh.bean.room.PlanRoom
import com.example.haa_roh.db.PHONEMES
import com.example.haa_roh.db.querySpNumber

/**
 * author : Haa-zzz
 * time : 2021/8/1
 *
 * Room的增删改查操作都放在这里统一管理，
 * 添加数据时需要在子线程进行。而查询数据时直接返回的是[LiveData]实例，在内部帮我们处理了线程问题
 */
private val perInfDao : PerInfDao = PersonalDatabase.getInstance(BaseApplication.getContext()).perInfDao()

private val planDao :PlanDao = PlanDatabase.getInstance(BaseApplication.getContext()).planDao()

private val number =  PHONEMES ?: querySpNumber()
//根据number来query数据
fun getDataFromRoom() : LiveData<PersonalInformation>?{
     number ?: return null
    return perInfDao.queryPersonalByNumber(number)
}
//添加数据
fun addDataToRoom(personalInformation : PersonalInformation){
    Thread {
        perInfDao.insertPersonal(personalInformation)
    }.start()
}

//向Plan中添加数据
fun addPlanToRoom(planRoom : PlanRoom){
    Thread {
        planDao.insertPlan(planRoom)
    }.start()
}
//向plan中查询数据
fun queryPlanFromRoom() : LiveData<List<PlanRoom>>?{
    number  ?: return null
    return planDao.queryPlanByNumber(number)
}
