package com.zainhumayun.fallout4terminalsolver.stattracking;

/**
 * Statistic class for lowest stat scores. Only lowest scores will be recorded
 **/
public class LowestStatistic extends BaseStatistic {
    public LowestStatistic(String key, String displayName, int statVal) {
        super(key, displayName, statVal);
    }

    @Override
    public void setNewVal(int newVal) {
        statVal = Math.min(statVal, newVal);
    }
}
