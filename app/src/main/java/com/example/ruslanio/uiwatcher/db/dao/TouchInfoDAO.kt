package com.example.ruslanio.uiwatcher.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.ruslanio.uiwatcher.db.base.BaseDao
import com.example.ruslanio.uiwatcher.db.model.ActivityUseInfo
import com.example.ruslanio.uiwatcher.db.model.TouchInfo

@Dao
abstract class TouchInfoDAO : BaseDao<TouchInfo>(){

    @Query("SELECT * FROM touch_info_table WHERE infoId = :activityInfoId")
    abstract fun selectAllByActivityInfoId(activityInfoId: Long) : List<TouchInfo>
}