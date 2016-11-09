package com.zainhumayun.fallout4terminalsolver.stattracking;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.zainhumayun.fallout4terminalsolver.R;
import java.util.ArrayList;
import java.util.List;

public class StatisticsManager {

    private static SharedPreferences statsHolder;

    private static List<BaseStatistic> statistics = new ArrayList<>();

    // keys
    public static final String STATS_KEY_SUM_TERMINALS_HACKED = "TERMINALS_HACKED";
    public static final String STATS_KEY_SUM_WORDS_ELIMINATED = "WORDS_ELIMINATED";

    // should only be called once
    public static void init(Application application){
        statsHolder = application.getSharedPreferences(application.getString(R.string.shared_pref_file_name), Context.MODE_PRIVATE);

        // load stats
        statistics.add(new SumStatistic(STATS_KEY_SUM_TERMINALS_HACKED, R.string.stats_terminals_eliminated_name, readPref(STATS_KEY_SUM_TERMINALS_HACKED)));
        statistics.add(new SumStatistic(STATS_KEY_SUM_WORDS_ELIMINATED, R.string.stats_words_eliminated, readPref(STATS_KEY_SUM_WORDS_ELIMINATED)));
    }

    private static int readPref(String key){
        if(statsHolder == null)
            return 0;

        return statsHolder.getInt(key, 0);
    }

    private static void writePref(String key, int val){
        SharedPreferences.Editor editor = statsHolder.edit();
        editor.putInt(key, val);
        editor.apply();
    }

    public static int getStat(String key){
        BaseStatistic stat = findStat(key);
        if(stat != null){
            return stat.getStatVal();
        }
        return 0;
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
