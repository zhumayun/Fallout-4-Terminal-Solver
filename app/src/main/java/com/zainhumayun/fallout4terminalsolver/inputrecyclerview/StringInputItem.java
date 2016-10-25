package com.zainhumayun.fallout4terminalsolver.inputrecyclerview;

// Wrapper class to hold reference to string, and update it
public class StringInputItem {
    private String s;

    public StringInputItem(String s){
        this.s = s;
    }

    public String getString(){
        return this.s;
    }

    public void setString(String s){
        this.s = s;
    }
}
