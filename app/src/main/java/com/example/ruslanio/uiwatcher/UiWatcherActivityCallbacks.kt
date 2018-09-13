package com.example.ruslanio.uiwatcher

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.ruslanio.uiwatcher.db.DBManager
import com.example.ruslanio.uiwatcher.db.model.ActivityUseInfo
import kotlin.reflect.KClass

class UiWatcherActivityCallbacks : Application.ActivityLifecycleCallbacks {

    private val visibleActivities = HashSet<KClass<out Activity>>()

    private var timeInUse: Long = 0

    private var context: Context
    private var dbManager: DBManager

    constructor(context: Context) {
        this.context = context
        dbManager = DBManager.getInstance(context)
    }


    override fun onActivityPaused(activity: Activity?) {
        if (activity != null) {
            var info = ActivityUseInfo(activityName = activity::class.java.name, time = getCurrentTime() - timeInUse)
            visibleActivities.remove(activity::class)
            dbManager.addInfo(info)
            System.out.println(info)
        }
    }

    override fun onActivityResumed(activity: Activity?) {
        if (activity != null) {
            timeInUse = getCurrentTime()
            visibleActivities.add(activity::class)
            if (visibleActivities.size == 0){
                onSessionEnd()
            }
        }
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }

    private fun getCurrentTime(): Long {
        return System.currentTimeMillis();
    }

    private fun onSessionEnd(){

    }
}