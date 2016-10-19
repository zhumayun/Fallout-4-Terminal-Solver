package com.zainhumayun.fallout4terminalsolver.Models;

/**
 * Model Class to contain information about likeness and the word
 **/
public class WordFilter {
    private String word;
    private int likeness;

    public WordFilter(String word, int likeness){
        this.word = word;
        this.likeness = likeness;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLikeness() {
        return likeness;
    }

    public void setLikeness(int likeness) {
        this.likeness = likeness;
    }
}
