package com.example.ruslanio.uiwatcher.db.model

import android.arch.persistence.room.Entity
import com.example.ruslanio.uiwatcher.db.base.BaseEntity

@Entity(tableName = "info_table")
class ActivityUseInfo constructor(var activityName: String,
                                  var time: Long) : BaseEntity() {

    override fun toString(): String {
        return "INFO : Activity ${activityName} was in use for ${time} seconds"
    }
}