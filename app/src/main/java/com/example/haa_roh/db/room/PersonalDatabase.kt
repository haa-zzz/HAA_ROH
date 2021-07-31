package com.example.haa_roh.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.haa_roh.bean.room.PersonalInformation


@Database(entities = [PersonalInformation::class],version = 1)
abstract class PersonalDatabase :  RoomDatabase(){

    abstract fun perInfDao() : PerInfDao

    companion object{
        private var databaseInstance: PersonalDatabase? = null
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): PersonalDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PersonalDatabase::class.java,
                    "personal"
                )
                    .build()
            }
            return databaseInstance as PersonalDatabase
        }
    }
}