package com.example.testapplication;

import com.example.ruslanio.uiwatcher.UiWatcherApplication;

public class TestApp extends UiWatcherApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        startWatching(true);
    }
}
