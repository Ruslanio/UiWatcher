package com.example.ruslanio.uiwatcher

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.ruslanio.uiwatcher.db.DBManager
import com.example.ruslanio.uiwatcher.db.model.ActivityUseInfo
import com.example.ruslanio.uiwatcher.db.model.TouchInfo
import kotlin.reflect.KClass

class UiWatcherActivityCallbacks : Application.ActivityLifecycleCallbacks {

    private val visibleActivities = HashSet<KClass<out Activity>>()

    private var timeUseStarted: Long = 0

    private var context: Context
    private var dbManager: DBManager

    private val LOG_TAG = "ui_watcher"

    private var currentActivityInfoId: Long? = null

    constructor(context: Context) {
        this.context = context
        dbManager = DBManager.getInstance(context)
    }


    override fun onActivityPaused(activity: Activity?) {
        if (activity != null) {
            val info = dbManager.getInfoById(currentActivityInfoId!!)
            info.time = (getCurrentTime() - timeUseStarted) / 1000



            visibleActivities.remove(activity::class)
            dbManager.updateInfo(info)
            Log.i(LOG_TAG, info.toString())
            sendToServer(info)
        }
    }

    override fun onActivityResumed(activity: Activity?) {
        if (activity != null) {

            setUpTouchViewer(activity)
            val info = ActivityUseInfo(activityName = activity::class.java.name, time = null)
            currentActivityInfoId = dbManager.addInfo(info)

            timeUseStarted = getCurrentTime()

            visibleActivities.add(activity::class)
            if (visibleActivities.size == 0) {
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

    private fun onSessionEnd() {

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpTouchViewer(activity: Activity?) {
        if (activity != null) {
            activity.window.decorView
            val frameLayout = FrameLayout(activity)
            val rootParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            frameLayout.layoutParams = rootParams

            frameLayout.setOnTouchListener { view, motionEvent ->
                if (currentActivityInfoId != null) {
                    val touchInfo = TouchInfo(motionEvent.rawX, motionEvent.rawY, currentActivityInfoId!!)
                    dbManager.addInfo(touchInfo)
                    Log.i(LOG_TAG, touchInfo.toString())
                }
                false

            }

            activity.window.addContentView(frameLayout, rootParams)
        }
    }

    private fun sendToServer(info: ActivityUseInfo) {
        //TODO - make api post when api will be done
        val touchList = dbManager.getAllTouchInfoByActivityInfoId(currentActivityInfoId!!)
        Log.i(LOG_TAG, info.toString(touchList))
    }
}