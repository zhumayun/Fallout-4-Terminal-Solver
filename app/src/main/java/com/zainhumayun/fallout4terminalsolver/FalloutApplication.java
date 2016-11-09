package com.zainhumayun.fallout4terminalsolver;

import android.app.Application;

import com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager;

public class FalloutApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StatisticsManager.init(this);
    }
}
