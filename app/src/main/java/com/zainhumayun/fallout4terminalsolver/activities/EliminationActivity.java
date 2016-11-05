package com.zainhumayun.fallout4terminalsolver.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zainhumayun.fallout4terminalsolver.LikenessDialogFragment;
import com.zainhumayun.fallout4terminalsolver.R;
import com.zainhumayun.fallout4terminalsolver.TerminalSolver;
import com.zainhumayun.fallout4terminalsolver.inputrecyclerview.EliminationRecyclerViewAdapter;
import com.zainhumayun.fallout4terminalsolver.models.WordFilter;

import java.util.List;

public class EliminationActivity extends HideActionBarActivity implements TerminalSolver.SolverListener, LikenessDialogFragment.DialogListener {

    private RecyclerView recyclerView;
    private Button backButton;
    private Button undoButton;
    private Button restartButton;
    private EliminationRecyclerViewAdapter adapter;

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
                adapter.getSolver().undo();
            }
        });
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EliminationActivity.this);
                builder.setTitle(R.string.alert_restart_title_text)
                        .setMessage(R.string.alert_restart_content_text)
                        .setPositiveButton(R.string.alert_input_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.getSolver().restart();
                            }
                        })
                        .setNegativeButton(R.string.alert_input_no, null)
                        .show();
            }
        });

        Intent givenIntent = getIntent();
        List<String> words = givenIntent.getStringArrayListExtra(getString(R.string.INTENT_KEY_INPUT_ITEMS));

        adapter = new EliminationRecyclerViewAdapter(words, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTerminalSolved(@NonNull String solvedWord) {
        // TODO
        adapter.onTerminalSolved(solvedWord);
    }

    @Override
    public void onUndoApplied(List<String> removedWords, WordFilter filter) {
        final boolean shouldEnableActions = adapter.getSolver().getHistoryDepth() > 0;
        undoButton.setEnabled(shouldEnableActions);
        restartButton.setEnabled(shouldEnableActions);

        adapter.onUndoApplied(removedWords, filter);
    }

    @Override
    public void onFilterApplied(List<String> removedWords, WordFilter filter) {
        final boolean shouldEnableActions = adapter.getSolver().getHistoryDepth() > 0;
        undoButton.setEnabled(shouldEnableActions);
        restartButton.setEnabled(shouldEnableActions);

        adapter.onFilterApplied(removedWords, filter);
    }

    @Override
    public void onRestarted(List<String> words) {
        undoButton.setEnabled(false);
        restartButton.setEnabled(false);

        adapter.onRestarted(words);

        Toast.makeText(this, R.string.toast_solver_restarted, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert_input_back_title_text)
                .setMessage(R.string.alert_elimination_back_content_text)
                .setPositiveButton(R.string.alert_input_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EliminationActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(R.string.alert_input_no, null)
                .show();
    }

    @Override
    public void onLikenessConfirmed(int wordIndex, int likeness) {
        adapter.onLikenessConfirmed(wordIndex, likeness);
    }

    @Override
    public void onCancel() {
        adapter.onCancel();
    }
}
