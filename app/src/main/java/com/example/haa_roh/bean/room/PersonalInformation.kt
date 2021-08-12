package com.example.haa_roh.bean.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * BMob数据库的Entity类
 * 更多操作看： [https://zhuanlan.zhihu.com/p/77036077]
 */
@Entity(tableName = "personal")
data class PersonalInformation(
    @PrimaryKey val number : String ,
    @ColumnInfo(name = "id")val id : String?,
    @ColumnInfo(name = "username")val username : String?,
    @ColumnInfo(name = "state")val state : String?,
    @ColumnInfo(name = "photo") val photo : String?
)

