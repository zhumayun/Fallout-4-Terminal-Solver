package com.zainhumayun.fallout4terminalsolver.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.zainhumayun.fallout4terminalsolver.R;
import com.zainhumayun.fallout4terminalsolver.inputrecyclerview.InputRecyclerViewAdapter;
import com.zainhumayun.fallout4terminalsolver.inputrecyclerview.StringInputItem;
import com.zainhumayun.fallout4terminalsolver.views.CondensedButton;

import java.util.ArrayList;

public class InputWordsActivity extends HideActionBarActivity {

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
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        viewAdapter = new InputRecyclerViewAdapter(new ArrayList<StringInputItem>());

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

                } else {
                    Toast.makeText(InputWordsActivity.this, "Please make sure all words have the same length.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
