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
                "BBBB", // password
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

    @Test
    public void basicUndoTest(){
        TerminalSolver solver = getSolverInstance(
                "HEAT",
                "KEEP",
                "REAP",
                "SOME",
                "HATE" // password
        );
        solver.undo();
        assertFalse(solver.isSolved());
        assertFalse(solver.haveAppliedFilters());
        assertEquals(0, solver.getHistoryDepth());

        solver.applyFilter(new WordFilter("HEAT", 1));
        assertTrue(solver.haveAppliedFilters());
        assertEquals(2, solver.getNumWordsLeft());

        solver.undo();
        assertFalse(solver.haveAppliedFilters());
        assertEquals(5, solver.getNumWordsLeft());
    }

    @Test
    public void multipleUndo(){
        TerminalSolver solver = getSolverInstance(
                "HATE",
                "HEAT",
                "HAVE",
                "REEK",
                "KEEP",
                "HEAP" // password
        );

        solver.applyFilter(new WordFilter("REEK", 1));
        solver.applyFilter(new WordFilter("HEAT", 3));
        assertTrue(solver.isSolved());
        assertEquals("HEAP", solver.getSolvedWord());

        solver.undo();
        solver.undo();

        assertFalse(solver.isSolved());
        assertEquals(6, solver.getNumWordsLeft());

        solver.applyFilter(new WordFilter("REEK", 1));
        solver.applyFilter(new WordFilter("HEAT", 3));

        assertTrue(solver.isSolved());
        assertEquals("HEAP", solver.getSolvedWord());
    }

    @Test
    public void basicRestart(){
        TerminalSolver solver = getSolverInstance(
                "AAAA",
                "AAAB",
                "AABB", // password
                "ABBB",
                "BBBB"
        );

        solver.restart();
        assertEquals(5, solver.getNumWordsLeft());
        solver.applyFilter(new WordFilter("BBBB", 2));
        assertTrue(solver.isSolved());
        assertEquals("AABB", solver.getSolvedWord());

        solver.restart();
        assertFalse(solver.isSolved());
        assertEquals(5, solver.getNumWordsLeft());
    }
}