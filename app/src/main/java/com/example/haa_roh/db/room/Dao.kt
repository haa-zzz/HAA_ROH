package com.example.haa_roh.db.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.haa_roh.bean.room.PersonalInformation
import com.example.haa_roh.bean.room.PlanRoom

/**
 * Room中的增删改查操作都要现在这里定义相关函数
 * 参考：[https://zhuanlan.zhihu.com/p/77036077]
 */
@Dao
interface PerInfDao {
    //添加数据
    @Insert
    fun insertPersonal( perInf : PersonalInformation)
    //查询数据
    @Query("SELECT * FROM personal WHERE number = :number")
    fun queryPersonalByNumber(number : String) : LiveData<PersonalInformation>
}
@Dao
interface PlanDao{
    //添加数据
    @Insert
    fun insertPlan( perInf : PlanRoom)
    //查询数据
    @Query("SELECT * FROM `plan` WHERE number = :number")
    fun queryPlanByNumber(number : String) : LiveData<List<PlanRoom>>
}