package com.zainhumayun.fallout4terminalsolver;

import com.zainhumayun.fallout4terminalsolver.stattracking.BaseStatistic;
import com.zainhumayun.fallout4terminalsolver.stattracking.HighestStatistic;
import com.zainhumayun.fallout4terminalsolver.stattracking.LowestStatistic;
import com.zainhumayun.fallout4terminalsolver.stattracking.SumStatistic;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Used to unit test the Statistics classes
 **/
public class StatisticsTest {

    @Test
    public void sumStatTest(){
        BaseStatistic statistic = new SumStatistic("", 0, 0);
        assertEquals(0, statistic.getStatVal());

        statistic.setNewVal(2);
        assertEquals(2, statistic.getStatVal());

        statistic.setNewVal(5);
        assertEquals(7, statistic.getStatVal());
    }

    @Test
    public void highestStatTest(){
        BaseStatistic statistic = new HighestStatistic("", 0, 0);
        assertEquals(0, statistic.getStatVal());

        statistic.setNewVal(2);
        assertEquals(2, statistic.getStatVal());

        statistic.setNewVal(1);
        assertEquals(2, statistic.getStatVal());

        statistic.setNewVal(5);
        assertEquals(5, statistic.getStatVal());
    }

    @Test
    public void lowestStatTest(){
        BaseStatistic statistic = new LowestStatistic("", 0, 2);
        assertEquals(2, statistic.getStatVal());

        statistic.setNewVal(2);
        assertEquals(2, statistic.getStatVal());

        statistic.setNewVal(1);
        assertEquals(1, statistic.getStatVal());

        statistic.setNewVal(5);
        assertEquals(1, statistic.getStatVal());
    }
}
