package com.example.haa_roh.db.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val MIGRATION_1_2 : Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        //此处对于数据库中的所有更新都需要写下面的代码
        database.execSQL(
            "ALTER TABLE users "
                    + " ADD COLUMN last_update INTEGER"
        )
    }
}