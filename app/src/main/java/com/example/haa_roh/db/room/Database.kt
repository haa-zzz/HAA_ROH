package com.example.haa_roh.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.haa_roh.bean.room.PersonalInformation
import com.example.haa_roh.bean.room.PlanRoom
/**
 * author : Haa-zzz
 * time : 2021/8/1
 *
 * 使用中遇到的问题：
 *
 * 1. 增删改查操作一定要放在 子线程
 * 2.
 *  出现的错误：Schema export directory is not provided to the annotation processor so we cannot export the schema.
 *  解决办法：在build gradle中添加
 *               defaultConfig {
...
javaCompileOptions {
annotationProcessorOptions {
arguments = ["room.schemaLocation":
"$projectDir/schemas".toString()]
}
}
}
 */
@Database(entities = [PersonalInformation::class], version = 1)
abstract class PersonalDatabase : RoomDatabase() {

    abstract fun perInfDao(): PerInfDao

    companion object {
        private var databaseInstance: PersonalDatabase? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): PersonalDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    context,
                    PersonalDatabase::class.java,
                    "personal"
                )
                    .build()
            }
            return databaseInstance as PersonalDatabase
        }
    }
}
@Database(entities = [PlanRoom::class], version = 2)
abstract class PlanDatabase : RoomDatabase() {
    abstract fun planDao(): PlanDao

    companion object {
        private var databaseInstance: PlanDatabase? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): PlanDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    context,
                    PlanDatabase::class.java,
                    "plan"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return databaseInstance as PlanDatabase
        }
    }
}