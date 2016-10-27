package com.zainhumayun.fallout4terminalsolver;

import com.zainhumayun.fallout4terminalsolver.models.WordFilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Container class that solves a Fallout Terminal puzzle
 **/
public class TerminalSolver {
    private List<String> wordsLeft;
    private List<WordFilter> filters = new ArrayList<>();
    private int wordSize = 0;

    /**
     * Pass in the full list of words here
     **/
    public TerminalSolver(List<String> wordsLeft){
        this.wordsLeft = wordsLeft;
        this.wordSize = wordsLeft.get(0).length();
    }

    public void applyFilter(WordFilter filter){
        Iterator<String> candidatesIterator = wordsLeft.iterator();

        while(candidatesIterator.hasNext()){
            if(!shouldKeep(filter, candidatesIterator.next()))
                candidatesIterator.remove();
        }
        // only add the filter if it's not the correct word
        if(filter.getWord().length() != wordSize) {
            wordsLeft.remove(filter.getWord());
            filters.add(filter);
        }
    }

    private boolean shouldKeep(WordFilter filter, String word){
        int similarChars = getNumberOfCharacterMatches(filter.getWord(), word);
        return similarChars != 0 && similarChars == filter.getLikeness();
    }

    private int getNumberOfCharacterMatches(String s1, String s2){
        int similarChars = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) == s2.charAt(i))
                similarChars++;
        }
        return similarChars;
    }

    public boolean isSolved(){
        return wordsLeft.size() == 1;
    }

    public String getSolvedWord(){
        if(!isSolved())
            return null;
        return wordsLeft.get(0);
    }
}
