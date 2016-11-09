package com.zainhumayun.fallout4terminalsolver.stattracking;

/**
 * Base class for Statistic classes
 **/
public abstract class BaseStatistic {
    private String key;
    private String displayName;
    protected int statVal;

    public String getPreferenceKey(){
        return key;
    }

    public String getDisplayName(){
        return displayName;
    }

    public int getStatVal(){
        return statVal;
    }

    public BaseStatistic(String key, String displayName, int statVal){
        this.key = key;
        this.displayName = displayName;
        this.statVal = statVal;
    }

    // sub classes should decide if they want to update their internal value based on the
    // given value. The shared preference class will write the value in the class after
    // calling this method
    public abstract void setNewVal(int newVal);
}
