package com.example.haa_roh.util

import androidx.lifecycle.LiveData
import com.example.haa_roh.base.BaseApplication
import com.example.haa_roh.bean.room.PersonalInformation
import com.example.haa_roh.db.querySpNumber
import com.example.haa_roh.db.room.PerInfDao
import com.example.haa_roh.db.room.PersonalDatabase

/**
 * author : Haa-zzz
 * time : 2021/8/1
 * Room的增删改查操作都放在这里统一管理
 */
private val perInfDao : PerInfDao = PersonalDatabase.getInstance(BaseApplication.getContext()).perInfDao()

//根据number来query数据
fun getDataFromRoom() : LiveData<PersonalInformation>?{
    val number  = querySpNumber() ?: return null
    return perInfDao.queryPersonalByNumber(number)
}
//添加数据
fun addDataToRoom(personalInformation : PersonalInformation){
    Thread {
        perInfDao.insertPersonal(personalInformation)
    }.start()
}