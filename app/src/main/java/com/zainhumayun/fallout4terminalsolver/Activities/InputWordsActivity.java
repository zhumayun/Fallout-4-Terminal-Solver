package com.zainhumayun.fallout4terminalsolver.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.zainhumayun.fallout4terminalsolver.R;
import com.zainhumayun.fallout4terminalsolver.inputrecyclerview.InputRecyclerViewAdapter;
import com.zainhumayun.fallout4terminalsolver.views.CondensedButton;

public class InputWordsActivity extends HideActionBarActivity implements InputRecyclerViewAdapter.DataSetSizeChangedListener {

    private CondensedButton backButton;
    private CondensedButton addButton;
    private CondensedButton nextButton;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private InputRecyclerViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_words);

        backButton = (CondensedButton) findViewById(R.id.input_words_button_back);
        addButton = (CondensedButton) findViewById(R.id.input_words_button_add);
        nextButton = (CondensedButton) findViewById(R.id.input_words_button_next);

        recyclerView = (RecyclerView) findViewById(R.id.input_words_input_recycler_view);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        viewAdapter = new InputRecyclerViewAdapter();
        viewAdapter.setOnNumberOfRowsChangedListener(this);

        recyclerView.setAdapter(viewAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAdapter.addRow();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewAdapter.isDataValid()){
                    Intent eliminationIntent = new Intent(InputWordsActivity.this, EliminationActivity.class);
                    eliminationIntent.putStringArrayListExtra(getString(R.string.INTENT_KEY_INPUT_ITEMS), viewAdapter.getDataSet());
                    startActivity(eliminationIntent);
                } else {
                    Toast.makeText(InputWordsActivity.this, "Please make sure all words have the same length.", Toast.LENGTH_LONG).show();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onNumberOfRowsChanged(int rows) {
        linearLayoutManager.scrollToPosition(0);
        nextButton.setEnabled(rows > 0);
    }

    @Override
    public void onBackPressed() {
        if(viewAdapter.getItemCount() == 0){
            super.onBackPressed();
            return;
        } 

        // Ask if they want to leave
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setTitle(R.string.alert_input_back_title_text)
                .setMessage(R.string.alert_input_back_content_text)
                .setPositiveButton(R.string.alert_input_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InputWordsActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(R.string.alert_input_no, null);
        builder.show();
    }
}
