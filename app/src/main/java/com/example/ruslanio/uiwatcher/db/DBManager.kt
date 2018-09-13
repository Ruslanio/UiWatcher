package com.example.ruslanio.uiwatcher.db

import android.content.Context
import com.example.ruslanio.uiwatcher.db.model.ActivityUseInfo
import com.example.ruslanio.uiwatcher.util.SingletonHolder

class DBManager private constructor(private var context: Context) {
    private var mainDatabase: MainDatabase = MainDatabase.getInstance(context)

    companion object : SingletonHolder<DBManager, Context>(::DBManager)

    fun addInfo(info: ActivityUseInfo){
        mainDatabase.infoDao().insert(info)
    }
}
