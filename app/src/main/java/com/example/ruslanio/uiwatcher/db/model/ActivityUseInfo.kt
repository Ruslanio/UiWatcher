package com.example.ruslanio.uiwatcher.db.model

import android.arch.persistence.room.Entity
import com.example.ruslanio.uiwatcher.db.base.BaseEntity

@Entity(tableName = "info_table")
class ActivityUseInfo constructor(var activityName: String,
                                  var time: Long?) : BaseEntity() {

    override fun toString(): String {
        return "{ \"activity\" : \"$activityName\", " +
                " \"time\" : $time, " +
                " \"touches\" : [] } "
    }

    fun toString(touches: List<TouchInfo>): String {
        val stringBuilder = StringBuilder()
        for (touch in touches) {
            stringBuilder.append(touch.toString()).append(",")
        }

        stringBuilder.setLength(stringBuilder.length - 1)

        return "{ \"activity\" : \"$activityName\", " +
                " \"time\" : $time, " +
                " \"touches\" : [$stringBuilder] } "
    }
}