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

    @Test
    public void noviceTest(){
        TerminalSolver solver = getSolverInstance(
                "FIVE",
                "FOUR"
        );
        assertEquals(TerminalSolver.Difficulty.NOVICE, solver.getDifficulty());

        solver = getSolverInstance(
                "FIVES",
                "FOURS"
        );

        assertEquals(TerminalSolver.Difficulty.NOVICE, solver.getDifficulty());
    }

    @Test
    public void advancedTest(){
        TerminalSolver solver = getSolverInstance(
                "666666",
                "777777",
                "888888"
        );

        assertEquals(TerminalSolver.Difficulty.ADVANCED, solver.getDifficulty());

        solver = getSolverInstance(
                "6666666",
                "7777777",
                "8888888"
        );

        assertEquals(TerminalSolver.Difficulty.ADVANCED, solver.getDifficulty());

        solver = getSolverInstance(
                "66666666",
                "77777777",
                "88888888"
        );

        assertEquals(TerminalSolver.Difficulty.ADVANCED, solver.getDifficulty());
    }

    @Test
    public void expertTest(){
        TerminalSolver solver = getSolverInstance(
                "999999999",
                "101010101"
        );

        assertEquals(TerminalSolver.Difficulty.EXPERT, solver.getDifficulty());

        solver = getSolverInstance(
                "9999999999",
                "1010101010"
        );

        assertEquals(TerminalSolver.Difficulty.EXPERT, solver.getDifficulty());
    }

    @Test
    public void masterTest(){
        TerminalSolver solver = getSolverInstance(
                "11111111111",
                "22222222222"
        );

        assertEquals(TerminalSolver.Difficulty.MASTER, solver.getDifficulty());

        solver = getSolverInstance(
                "111111111111",
                "222222222222"
        );

        assertEquals(TerminalSolver.Difficulty.MASTER, solver.getDifficulty());
    }

    @Test
    public void unknownTest(){
        TerminalSolver solver = getSolverInstance(
                "333"
        );

        assertEquals(TerminalSolver.Difficulty.UNKNOWN, solver.getDifficulty());

        solver = getSolverInstance(
                "2342359238752349857239875"
        );

        assertEquals(TerminalSolver.Difficulty.UNKNOWN, solver.getDifficulty());
    }
}