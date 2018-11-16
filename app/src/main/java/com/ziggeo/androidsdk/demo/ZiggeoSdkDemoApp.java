package com.ziggeo.androidsdk.demo;

import android.app.Application;

import com.ziggeo.demo.BuildConfig;

import timber.log.Timber;

public class ZiggeoSdkDemoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
