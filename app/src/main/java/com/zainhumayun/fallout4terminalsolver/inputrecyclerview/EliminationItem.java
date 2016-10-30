package com.zainhumayun.fallout4terminalsolver.inputrecyclerview;

public class EliminationItem {
    private int viewType;
    private String word;

    public EliminationItem(int viewType, String word){
        this.viewType = viewType;
        this.word = word;
    }

    public EliminationItem(String word){
        this(EliminationRecyclerViewAdapter.WORD, word);
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
