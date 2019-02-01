package com.ziggeo.androidsdk.demo;

import android.support.multidex.MultiDexApplication;

import com.ziggeo.demo.BuildConfig;

import timber.log.Timber;

public class ZiggeoSdkDemoApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
