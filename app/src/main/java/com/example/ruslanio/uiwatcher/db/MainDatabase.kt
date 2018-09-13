package com.example.ruslanio.uiwatcher.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.icu.text.IDNA
import com.example.ruslanio.uiwatcher.db.dao.InfoDAO
import com.example.ruslanio.uiwatcher.db.model.ActivityUseInfo

@Database(entities = arrayOf(ActivityUseInfo::class), version = 1)
abstract class MainDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "ui_watcher_database";

        fun getInstance(context: Context) = Room.databaseBuilder(context, MainDatabase::class.java, DB_NAME)
                .allowMainThreadQueries() //TODO remove
                .build()
    }

    abstract fun infoDao() : InfoDAO
}