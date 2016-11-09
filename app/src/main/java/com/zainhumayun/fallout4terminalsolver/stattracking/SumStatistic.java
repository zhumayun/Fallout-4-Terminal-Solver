package com.zainhumayun.fallout4terminalsolver.stattracking;

/**
 * Statistic class for stats that aggregate (or sum) their values
 **/
public class SumStatistic extends BaseStatistic {
    public SumStatistic(String key, String displayName, int statVal) {
        super(key, displayName, statVal);
    }

    @Override
    public void setNewVal(int newVal) {
        statVal = newVal;
    }
}
