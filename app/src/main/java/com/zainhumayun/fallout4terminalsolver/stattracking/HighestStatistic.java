package com.zainhumayun.fallout4terminalsolver.stattracking;

/**
 * Statistic class for best score stats. Only highest scores will be recorded.
 **/
public class HighestStatistic extends BaseStatistic {
    public HighestStatistic(String key, String displayName, int statVal) {
        super(key, displayName, statVal);
    }

    @Override
    public void setNewVal(int newVal) {
        statVal = Math.max(statVal, newVal);
    }
}
