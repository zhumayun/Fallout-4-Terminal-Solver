package com.zainhumayun.fallout4terminalsolver.stattracking;

import android.support.annotation.StringRes;

/**
 * Statistic class for lowest stat scores. Only lowest scores will be recorded
 **/
public class LowestStatistic extends BaseStatistic {
    public LowestStatistic(String key, @StringRes int displayName, int statVal) {
        super(key, displayName, statVal);
    }

    @Override
    public void setNewVal(int newVal) {
        statVal = Math.min(statVal, newVal);
    }
}
