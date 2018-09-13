package com.example.ruslanio.uiwatcher;

import android.app.Application;

public class UiWatcherApplication extends Application {

    private boolean isWatching = false;

    private ActivityLifecycleCallbacks activityLifecycleCallbacks;


    /*If you are overriding this method, you need to call super.OnCreate()*/
    @Override
    public void onCreate() {
        super.onCreate();
        activityLifecycleCallbacks = new UiWatcherActivityCallbacks(this);
    }

    public boolean isWatching () {
        return isWatching;
    }

    public void startWatching(boolean isWatching){
        this.isWatching = isWatching;
        if (isWatching){
            registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        } else {
            unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

}
