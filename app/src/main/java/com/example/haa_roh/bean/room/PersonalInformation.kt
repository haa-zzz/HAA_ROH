package com.example.haa_roh.bean.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personal")
data class PersonalInformation(

    @PrimaryKey val number : String? ,
    @ColumnInfo(name = "id")val id : String?,
    @ColumnInfo(name = "username")val username : String?,
    @ColumnInfo(name = "state")val state : String?,
    @ColumnInfo(name = "photo") val photo : String?



)