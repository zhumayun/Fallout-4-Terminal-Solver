package com.zainhumayun.fallout4terminalsolver;
import com.zainhumayun.fallout4terminalsolver.models.WordFilter;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit Testing class to test {@link TerminalSolver}
 **/
public class TerminalSolverTest {

    private TerminalSolver getSolverInstance(String ... items){
        return new TerminalSolver(new ArrayList<>(Arrays.asList(items)));
    }

    @Test
    public void basicNotSolvedTest(){
        TerminalSolver solver = getSolverInstance(
                "HEAT",
                "KEEP",
                "FEET"
        );
        assertFalse(solver.isSolved());
    }

    @Test
    public void basicSolvedTest(){
        TerminalSolver solver = getSolverInstance("KEEP");
        assertTrue(solver.isSolved());
        solver.applyFilter(new WordFilter("KEEP", 4));
        assertTrue(solver.isSolved());
    }

    @Test
    public void basicGame(){
        TerminalSolver solver = getSolverInstance(
                "BEAT",
                "KEEP",
                "REEK",
                "MEET"
        );
        solver.applyFilter(new WordFilter("MEET", 2));
        assertEquals(3, solver.getNumWordsLeft());
        solver.applyFilter(new WordFilter("KEEP", 1));
        assertTrue(solver.isSolved());
    }

    @Test
    public void zeroLikeness(){
        TerminalSolver solver = getSolverInstance(
                "AAAA",
                "BBBB",
                "CCCC",
                "DDDD"
        );
        solver.applyFilter(new WordFilter("CCCC", 0));
        assertEquals(3, solver.getNumWordsLeft());
        solver.applyFilter(new WordFilter("DDDD", 0));
        assertEquals(2, solver.getNumWordsLeft());
        solver.applyFilter(new WordFilter("BBBB", 4));
        assertTrue(solver.isSolved());
        assertEquals("BBBB", solver.getSolvedWord());
    }

    @Test
    public void palandromeTest(){
        TerminalSolver solver = getSolverInstance(
                "MEAT",
                "TAEM"
        );
        solver.applyFilter(new WordFilter("MEAT", 0));
        assertTrue(solver.isSolved());
        assertEquals("TAEM", solver.getSolvedWord());
    }
}