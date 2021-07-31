package com.example.haa_roh.repository

import androidx.lifecycle.MutableLiveData
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import com.example.haa_roh.bean.*
import com.example.haa_roh.db.queryUser
import com.example.haa_roh.util.oneMinuteCountdown

fun bMobSMS(phoneNumber : String, loginGetAutoCode : MutableLiveData<LoginAutoCode>,
            loginCountNumber : MutableLiveData<CountChange?>) {

    BmobSMS.requestSMSCode(phoneNumber, "", object : QueryListener<Int>() {
        override fun done(smsId : Int?, e: BmobException?) {
            if (smsId != null) {
                loginGetAutoCode.value = LoginAutoCode(success = smsId)
                oneMinuteCountdown(loginCountNumber)
            } else {
                loginGetAutoCode.value = LoginAutoCode(error = e.toString())
            }
        }
    })
}

fun bMobSMSVerify(phone : String , code : String, loginResult : MutableLiveData<LoginResult>){
    BmobSMS.verifySmsCode(phone,code,object : UpdateListener(){
        override fun done(e: BmobException?) {
            if(e == null){
                saveToSp(phone,true)
                loginResult.value = LoginResult(success = true)
            }else{
                loginResult.value = LoginResult(success = false,error = e.toString())
            }
        }
    })
}

