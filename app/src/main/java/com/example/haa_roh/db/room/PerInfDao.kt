package com.example.haa_roh.db.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.haa_roh.bean.room.PersonalInformation

@Dao
interface PerInfDao {
    //添加数据
    @Insert
    fun insertPersonal( perInf : PersonalInformation)
    //查询数据
    @Query("SELECT * FROM personal WHERE number = :number")
    fun queryPersonalByNumber(number : String) : LiveData<PersonalInformation>
}