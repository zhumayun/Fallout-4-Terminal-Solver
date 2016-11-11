package com.zainhumayun.fallout4terminalsolver.activities;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zainhumayun.fallout4terminalsolver.R;
import com.zainhumayun.fallout4terminalsolver.stattracking.BaseStatistic;
import com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager;

import java.util.List;

import static com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager.STATS_KEY_HIGHEST_MATCHED_WORD;
import static com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager.STATS_KEY_LOWEST_GUESSES;
import static com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager.STATS_KEY_MOST_ADVANCED;
import static com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager.STATS_KEY_MOST_EXPERT;
import static com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager.STATS_KEY_MOST_MASTER;
import static com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager.STATS_KEY_MOST_NOVICE;
import static com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager.STATS_KEY_MOST_UNKNOWN;
import static com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager.STATS_KEY_SUM_TERMINALS_HACKED;
import static com.zainhumayun.fallout4terminalsolver.stattracking.StatisticsManager.STATS_KEY_SUM_WORDS_ELIMINATED;

public class StatisticsActivity extends HideActionBarActivity {
    private LinearLayout totalContainer, bestContainer, difficultyContainer;

    // Search presets below. Are used to query for sets of similar stats
    private static final String[] totalScoreSearchSet = {
            STATS_KEY_SUM_TERMINALS_HACKED,
            STATS_KEY_SUM_WORDS_ELIMINATED
    };

    private static final String[] bestScoreSearchSet = {
            STATS_KEY_LOWEST_GUESSES,
            STATS_KEY_HIGHEST_MATCHED_WORD
    };

    private static final String[] difficultySearchSet = {
            STATS_KEY_MOST_NOVICE,
            STATS_KEY_MOST_ADVANCED,
            STATS_KEY_MOST_EXPERT,
            STATS_KEY_MOST_MASTER,
            STATS_KEY_MOST_UNKNOWN
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        totalContainer = (LinearLayout) findViewById(R.id.stats_total_container);
        bestContainer = (LinearLayout) findViewById(R.id.stats_best_scores_container);
        difficultyContainer = (LinearLayout) findViewById(R.id.stats_difficulty_container);

        // populate containers
        populateContainer(totalScoreSearchSet, totalContainer);
        populateContainer(bestScoreSearchSet, bestContainer);
        populateContainer(difficultySearchSet, difficultyContainer);
    }

    private void populateContainer(String[] searchSet, LinearLayout container){
        List<BaseStatistic> statistics = StatisticsManager.getStatistics(searchSet);

        for(BaseStatistic statistic : statistics){
            View rootView = LayoutInflater.from(this).inflate(R.layout.stat_row, container, false);

            TextView title = (TextView) rootView.findViewById(R.id.stat_row_text);
            TextView score = (TextView) rootView.findViewById(R.id.stat_row_value);

            title.setText(statistic.getDisplayName());

            if(statistic.getStatVal() >= 0)
                score.setText("" + statistic.getStatVal());
            else
                score.setText("--");

            container.addView(rootView);
        }
    }
}
