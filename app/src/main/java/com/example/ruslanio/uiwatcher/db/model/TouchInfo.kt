package com.example.ruslanio.uiwatcher.db.model

import android.arch.persistence.room.Entity
import com.example.ruslanio.uiwatcher.db.base.BaseEntity

@Entity(tableName = "touch_info_table")
class TouchInfo constructor(var x: Float, var y: Float, var infoId: Long) : BaseEntity() {

    override fun toString(): String {
        return "{\"x\" : $x, " +
                "\"y\" : $y}"
    }
}