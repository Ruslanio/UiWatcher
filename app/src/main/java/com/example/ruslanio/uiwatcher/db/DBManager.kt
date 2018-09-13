package com.example.ruslanio.uiwatcher.db

import android.content.Context
import com.example.ruslanio.uiwatcher.db.model.ActivityUseInfo
import com.example.ruslanio.uiwatcher.db.model.TouchInfo
import com.example.ruslanio.uiwatcher.util.SingletonHolder

class DBManager private constructor(private var context: Context) {
    private var mainDatabase: MainDatabase = MainDatabase.getInstance(context)

    companion object : SingletonHolder<DBManager, Context>(::DBManager)

    fun addInfo(info: ActivityUseInfo): Long {
        return mainDatabase.infoDao().insert(info)
    }

    fun addInfo(info: TouchInfo): Long {
        return mainDatabase.touchInfoDao().insert(info)
    }

    fun updateInfo(info: ActivityUseInfo){
        mainDatabase.infoDao().update(info)
    }

    fun getInfoById(id : Long): ActivityUseInfo{
        return mainDatabase.infoDao().selectById(id)
    }

    fun getAllTouchInfoByActivityInfoId(activityInfoId : Long) : List<TouchInfo>{
        return mainDatabase.touchInfoDao().selectAllByActivityInfoId(activityInfoId)
    }
}
