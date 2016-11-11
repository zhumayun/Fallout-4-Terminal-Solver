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

        /*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

            ShortcutInfo startShortCut = new ShortcutInfo.Builder(this, "startnew")
                    .setShortLabel(getString(R.string.shortcut_start_new_short_label))
                    .setLongLabel(getString(R.string.shortcut_start_new_long_label))
                    .setDisabledMessage(getString(R.string.shortcut_start_new_disabled))
                    .setIntent(new Intent(this, InputWordsActivity.class))
                    .build();
            shortcutManager.setDynamicShortcuts(Arrays.asList(startShortCut));
        }*/
    }
}
