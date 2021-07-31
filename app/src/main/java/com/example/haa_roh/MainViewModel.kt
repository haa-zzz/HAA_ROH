package com.example.haa_roh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.haa_roh.base.BaseApplication.Companion.getContext
import com.example.haa_roh.base.BaseViewModel
import com.example.haa_roh.bean.room.PersonalInformation
import com.example.haa_roh.db.room.PersonalDatabase

class MainViewModel : BaseViewModel() {


    private lateinit var  initPersonalInformation : LiveData<PersonalInformation>

    fun getPIFromBMob(){

    }

    fun getPIFromDb(number : String){
        val personalDatabase = PersonalDatabase.getInstance(getContext())
        initPersonalInformation = personalDatabase.perInfDao().queryPersonalByNumber(number)
    }

    fun getInitInformation() : LiveData<PersonalInformation>{
        return initPersonalInformation
    }


}