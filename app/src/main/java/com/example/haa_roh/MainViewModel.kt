package com.example.haa_roh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haa_roh.base.BaseViewModel
import com.example.haa_roh.bean.DataResult
import com.example.haa_roh.db.querySpNumber
import com.example.haa_roh.bean.room.PersonalInformation

import com.example.haa_roh.db.queryUser
import com.example.haa_roh.util.LOGGED
import com.example.haa_roh.util.addDataToRoom
import com.example.haa_roh.util.getDataFromRoom

/**
 * author : Haa-zzz
 * time : 2021/8/1
 * MainActivity对应的ViewModel
 */
class MainViewModel : BaseViewModel() {

    //接收Room数据库的LiveData
    private lateinit var  initPersonalInformation : LiveData<PersonalInformation>
    //接收BMob数据的LiveData
    private val _bMobResult =  MutableLiveData<DataResult>()
    val bMobResult : LiveData<DataResult> = _bMobResult

    /*
     * 从BMob获取数据
     */
    fun getPIFromBMob(){
        val number = querySpNumber() ?: return
        queryUser(number,_bMobResult)
    }
    /*
    从Room获取数据
     */
    fun getPIFromRoom(){
        initPersonalInformation = getDataFromRoom() ?: return
    }
    fun addPIToRoom(personalInformation : PersonalInformation?){
        if (personalInformation != null) {
            addDataToRoom(personalInformation)
            getPIFromRoom()
        }
    }
    fun getInitInformation() : LiveData<PersonalInformation>{
        return initPersonalInformation
    }
}