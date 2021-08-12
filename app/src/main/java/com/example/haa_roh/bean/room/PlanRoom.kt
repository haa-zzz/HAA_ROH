package com.example.haa_roh.bean.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plan")
data class PlanRoom(
    //@PrimaryKey val number : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "number")val number : String?,
    @ColumnInfo(name = "title")val title : String?,
    @ColumnInfo(name = "tag")val tag : String?,
    @ColumnInfo(name = "isFinish")val isFinish : Boolean?,
    @ColumnInfo(name = "content")val content: String?,
)
