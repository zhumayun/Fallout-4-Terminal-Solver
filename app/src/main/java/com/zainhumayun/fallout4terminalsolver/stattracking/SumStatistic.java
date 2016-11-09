package com.zainhumayun.fallout4terminalsolver.stattracking;

import android.support.annotation.StringRes;

/**
 * Statistic class for stats that aggregate (or sum) their values
 **/
public class SumStatistic extends BaseStatistic {
    public SumStatistic(String key, @StringRes int displayName, int statVal) {
        super(key, displayName, statVal);
    }

    @Override
    public void setNewVal(int newVal) {
        statVal += newVal;
    }
}
