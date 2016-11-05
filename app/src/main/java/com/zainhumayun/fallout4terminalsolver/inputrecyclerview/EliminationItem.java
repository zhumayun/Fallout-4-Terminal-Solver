package com.zainhumayun.fallout4terminalsolver.inputrecyclerview;

public class EliminationItem {
    private int viewType;
    private String word;
    private int likeness;

    public EliminationItem(int viewType, String word){
        this.viewType = viewType;
        this.word = word;
    }

    public EliminationItem(int viewType, String word, int likeness){
        this(viewType, word);
        this.likeness = likeness;
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

    public int getLikeness() {
        return likeness;
    }

    public void setLikeness(int likeness) {
        this.likeness = likeness;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof EliminationItem){
            EliminationItem other = (EliminationItem) o;
            // don't care about likeness
            return other.viewType == this.viewType
                    && other.word.equals(this.word);
        } else {
            return false;
        }
    }
}

