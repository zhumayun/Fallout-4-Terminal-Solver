package com.zainhumayun.fallout4terminalsolver.inputrecyclerview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zainhumayun.fallout4terminalsolver.R;
import com.zainhumayun.fallout4terminalsolver.TerminalSolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for Word Elimination Screen
 **/
public class EliminationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected static final int WORD = 0;// word left
    protected static final int FILTER = 1; // word filter
    protected static final int ELIMINATED = 2; // eliminated word
    protected static final int NONE = 3; // spacing for row

    private String TAG = "EliminationAdapter";
    private TerminalSolver solver;
    private List<EliminationItem> dataset = new ArrayList<>();
    private Activity activity;

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        public WordViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.elim_row_text);
            itemView.setOnClickListener(this);
        }

        public void bind(String word){
            textView.setText(word);
        }

        @Override
        public void onClick(View v) {
            EliminationRecyclerViewAdapter.this.onViewHolderClicked(getAdapterPosition());
        }
    }

    public EliminationRecyclerViewAdapter(List<String> words, TerminalSolver.SolverListener listener, Activity activity){
        for(String word : words){
            dataset.add(new EliminationItem(word));
        }

        solver = new TerminalSolver(words);
        solver.setSolverListener(listener);
        this.activity = activity;
    }

    public void onViewHolderClicked(int position){
        Log.i(TAG, "View Holder clicked: position = " + position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int type = dataset.get(position).getViewType();

        switch (type){
            case WORD:
                ((WordViewHolder)holder).bind(dataset.get(position).getWord());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType){
            case WORD:
                viewHolder = new WordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.elim_row_word_left, parent, false));
                break;
            default:
                viewHolder = null;
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return dataset.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public TerminalSolver getSolver(){
        return this.solver;
    }
}
