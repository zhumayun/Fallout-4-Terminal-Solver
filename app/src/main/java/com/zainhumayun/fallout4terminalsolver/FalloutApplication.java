package com.zainhumayun.fallout4terminalsolver;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.os.Build;

import com.zainhumayun.fallout4terminalsolver.activities.InputWordsActivity;
import com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager;

import java.util.Arrays;

public class FalloutApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StatisticsManager.init(this);
    }
}
