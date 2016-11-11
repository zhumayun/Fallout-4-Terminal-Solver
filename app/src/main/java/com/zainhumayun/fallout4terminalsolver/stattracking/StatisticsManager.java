package com.zainhumayun.fallout4terminalsolver.stattracking;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.zainhumayun.fallout4terminalsolver.R;
import com.zainhumayun.fallout4terminalsolver.TerminalSolver;

import java.util.ArrayList;
import java.util.List;

public class StatisticsManager {

    private static SharedPreferences statsHolder;

    private static List<BaseStatistic> statistics = new ArrayList<>();

    // keys
    public static final String STATS_KEY_SUM_TERMINALS_HACKED = "TERMINALS_HACKED";
    public static final String STATS_KEY_SUM_WORDS_ELIMINATED = "WORDS_ELIMINATED";
    public static final String STATS_KEY_LOWEST_GUESSES = "LEAST_GUESSES";
    public static final String STATS_KEY_HIGHEST_MATCHED_WORD = "HIGHEST_WORD";
    public static final String STATS_KEY_MOST_NOVICE = "NOVICE_SUM";
    public static final String STATS_KEY_MOST_ADVANCED = "ADVANCED_SUM";
    public static final String STATS_KEY_MOST_EXPERT = "EXPERT_SUM";
    public static final String STATS_KEY_MOST_MASTER = "MASTER_SUM";
    public static final String STATS_KEY_MOST_UNKNOWN = "UNKNOWN_SUM";


    // should only be called once
    public static void init(Application application){
        statsHolder = application.getSharedPreferences(application.getString(R.string.shared_pref_file_name), Context.MODE_PRIVATE);

        // load stats
        statistics.add(new SumStatistic(STATS_KEY_SUM_TERMINALS_HACKED, R.string.stats_terminals_eliminated_name, readPref(STATS_KEY_SUM_TERMINALS_HACKED)));
        statistics.add(new SumStatistic(STATS_KEY_SUM_WORDS_ELIMINATED, R.string.stats_words_eliminated, readPref(STATS_KEY_SUM_WORDS_ELIMINATED)));
        statistics.add(new LowestStatistic(STATS_KEY_LOWEST_GUESSES, R.string.stats_least_guesses, readPref(STATS_KEY_LOWEST_GUESSES, -1)));
        statistics.add(new HighestStatistic(STATS_KEY_HIGHEST_MATCHED_WORD, R.string.stats_longest_word, readPref(STATS_KEY_HIGHEST_MATCHED_WORD)));
        // difficulty stats
        statistics.add(new SumStatistic(STATS_KEY_MOST_NOVICE, R.string.stats_solved_amount_novice, readPref(STATS_KEY_MOST_NOVICE)));
        statistics.add(new SumStatistic(STATS_KEY_MOST_ADVANCED, R.string.stats_solved_amount_advanced, readPref(STATS_KEY_MOST_ADVANCED)));
        statistics.add(new SumStatistic(STATS_KEY_MOST_EXPERT, R.string.stats_solved_amount_expert, readPref(STATS_KEY_MOST_EXPERT)));
        statistics.add(new SumStatistic(STATS_KEY_MOST_MASTER, R.string.stats_solved_amount_master, readPref(STATS_KEY_MOST_MASTER)));
        statistics.add(new SumStatistic(STATS_KEY_MOST_UNKNOWN, R.string.stats_solved_amount_unknown, readPref(STATS_KEY_MOST_UNKNOWN)));
    }

    private static int readPref(String key){
        return readPref(key, 0);
    }

    private static int readPref(String key, int defaultVal){
        if(statsHolder == null)
            return defaultVal;

        return statsHolder.getInt(key, defaultVal);
    }

    private static void writePref(String key, int val){
        SharedPreferences.Editor editor = statsHolder.edit();
        editor.putInt(key, val);
        editor.apply();
    }

    public static List<BaseStatistic> getStatistics(String[] searchSet){
        List<BaseStatistic> foundStats = new ArrayList<>();
        for(String key : searchSet){
            BaseStatistic statistic = findStat(key);
            if(key != null)
                foundStats.add(statistic);
        }

        return foundStats;
    }

    public static int getStatistic(String key){
        BaseStatistic stat = findStat(key);
        if(stat != null){
            return stat.getStatVal();
        }
        return 0;
    }

    public static void updateDifficultyStat(TerminalSolver.Difficulty difficulty){
        String key = STATS_KEY_MOST_UNKNOWN;

        switch (difficulty){
            case NOVICE:
                key = STATS_KEY_MOST_NOVICE;
                break;
            case ADVANCED:
                key = STATS_KEY_MOST_ADVANCED;
                break;
            case EXPERT:
                key = STATS_KEY_MOST_EXPERT;
                break;
            case MASTER:
                key = STATS_KEY_MOST_MASTER;
                break;
        }

        updateStat(key, 1);
    }

    public static void updateStat(String key, int newVal){
        // find the stat
        BaseStatistic stat = findStat(key);
        if(stat != null) {
            stat.setNewVal(newVal);
            writePref(key, stat.getStatVal());
        }
    }

    private static BaseStatistic findStat(String key){
        for(BaseStatistic statistic : statistics){
            if(statistic.getPreferenceKey().equals(key)){
                return statistic;
            }
        }
        return null;
    }
}
