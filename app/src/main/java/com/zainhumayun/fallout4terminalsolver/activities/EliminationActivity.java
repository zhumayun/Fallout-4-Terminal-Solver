package com.zainhumayun.fallout4terminalsolver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.zainhumayun.fallout4terminalsolver.R;
import com.zainhumayun.fallout4terminalsolver.TerminalSolver;
import com.zainhumayun.fallout4terminalsolver.models.WordFilter;

import java.util.List;

public class EliminationActivity extends HideActionBarActivity implements TerminalSolver.SolverListener {

    private RecyclerView recyclerView;
    private Button backButton;
    private Button undoButton;
    private Button restartButton;
    private TerminalSolver solver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elimination);

        recyclerView = (RecyclerView) findViewById(R.id.word_elim_recycler_view);
        backButton = (Button) findViewById(R.id.word_elim_button_back);
        undoButton = (Button) findViewById(R.id.word_elim_button_undo);
        restartButton = (Button) findViewById(R.id.word_elim_button_restart);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solver.undo();
            }
        });
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solver.restart();
            }
        });

        Intent givenIntent = getIntent();

        solver = new TerminalSolver(givenIntent.getStringArrayListExtra(getString(R.string.INTENT_KEY_INPUT_ITEMS)));
        solver.setSolverListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onTerminalSolved(@NonNull String solvedWord) {
        // TODO
    }

    @Override
    public void onUndoApplied(List<String> removedWords, WordFilter filter) {
        // TODO
        final boolean shouldEnableActions = solver.getHistoryDepth() > 0;
        undoButton.setEnabled(shouldEnableActions);
        restartButton.setEnabled(shouldEnableActions);
    }

    @Override
    public void onFilterApplied(List<String> removedWords, WordFilter filter) {
        // TODO
    }

    @Override
    public void onRestarted() {
        // TODO
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // TODO
    }
}
