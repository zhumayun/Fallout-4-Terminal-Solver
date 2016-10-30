package com.zainhumayun.fallout4terminalsolver.inputrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zainhumayun.fallout4terminalsolver.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for Word Elimination Screen
 **/
public class EliminationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected static final int WORD = 0;// word left
    protected static final int FILTER = 1; // word filter
    protected static final int ELIMINATED = 2; // eliminated word
    protected static final int NONE = 3; // spacing for row

    private List<EliminationItem> dataset = new ArrayList<>();

    public class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public WordViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.elim_row_text);
        }

        public void bind(String word){
            textView.setText(word);
        }
    }

    public EliminationRecyclerViewAdapter(List<String> words){
        for(String word : words){
            dataset.add(new EliminationItem(word));
        }
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
}
