package com.example.haa_roh.bean.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plan")
data class PlanRoom(
    //@PrimaryKey val number : String,

    //用id作为唯一键，方便用来删除
    @PrimaryKey
    val id : String,
    @ColumnInfo(name = "number")val number : String? = null,
    @ColumnInfo(name = "title")val title : String? = null,
    @ColumnInfo(name = "tag")val tag : String? = null,
    @ColumnInfo(name = "isFinish")val isFinish : Boolean? = null,
    @ColumnInfo(name = "content")val content: String? = null
)
