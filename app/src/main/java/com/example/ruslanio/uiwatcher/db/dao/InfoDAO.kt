package com.example.ruslanio.uiwatcher.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.ruslanio.uiwatcher.db.base.BaseDao
import com.example.ruslanio.uiwatcher.db.model.ActivityUseInfo
import java.sql.RowId

@Dao
abstract class InfoDAO : BaseDao<ActivityUseInfo>() {

    @Query("SELECT * FROM info_table")
    abstract fun selectAll() : List<ActivityUseInfo>

    @Query("SELECT * FROM info_table WHERE id = :id LIMIT 1")
    abstract fun selectById(id: Long) : ActivityUseInfo
}