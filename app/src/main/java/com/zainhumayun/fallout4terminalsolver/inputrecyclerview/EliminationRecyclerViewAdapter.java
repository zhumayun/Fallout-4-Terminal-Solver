package com.zainhumayun.fallout4terminalsolver.inputrecyclerview;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zainhumayun.fallout4terminalsolver.LikenessDialogFragment;
import com.zainhumayun.fallout4terminalsolver.R;
import com.zainhumayun.fallout4terminalsolver.TerminalSolver;
import com.zainhumayun.fallout4terminalsolver.models.WordFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Adapter for Word Elimination Screen
 **/
public class EliminationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements LikenessDialogFragment.DialogListener, TerminalSolver.SolverListener {

    protected static final int WORD = 0;// word left
    protected static final int FILTER = 1; // word filter
    protected static final int ELIMINATED = 2; // eliminated word
    protected static final int NONE = 3; // spacing for row

    private String TAG = "EliminationAdapter";
    private TerminalSolver solver;

    // Elimination items are split into 3 lists so that items can be inserted into the correct location easily
    private List<EliminationItem> wordsLeftDataSet = new ArrayList<>();
    private List<EliminationItem> filterWordsDataSet = new ArrayList<>();
    private List<EliminationItem> eliminatedWordsDataSet = new ArrayList<>();

    private AppCompatActivity activity;

    /**
     * ViewHolder for Words left
     **/
    private class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView wordText;
        private WordViewHolder(View itemView) {
            super(itemView);
            wordText = (TextView) itemView.findViewById(R.id.elim_row_text);
            itemView.setOnClickListener(this);
        }

        private void bind(String word){
            wordText.setText(word);
        }

        @Override
        public void onClick(View v) {
            EliminationRecyclerViewAdapter.this.onViewHolderClicked(getAdapterPosition());
        }
    }

    /**
     * ViewHolder for Word Filters
     **/
    private class FilterWordViewHolder extends RecyclerView.ViewHolder {
        private TextView wordText, likenessText;

        private FilterWordViewHolder(View itemView){
            super(itemView);
            wordText = (TextView) itemView.findViewById(R.id.elim_row_text);
            likenessText = (TextView) itemView.findViewById(R.id.elim_row_likeness_text);
        }

        private void bind(String word, int likeness){
            wordText.setText(word);
            likenessText.setText("" + likeness);
        }
    }

    /**
     * ViewHolder for Eliminated Word
     **/
    private class EliminatedWordViewHolder extends RecyclerView.ViewHolder {
        private TextView wordText;

        private EliminatedWordViewHolder(View itemView){
            super(itemView);
            wordText = (TextView) itemView.findViewById(R.id.elim_row_text);
        }

        private void bind(String word){
            wordText.setText(word);
            // make it strike through
            wordText.setPaintFlags(wordText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }


    public EliminationRecyclerViewAdapter(List<String> words, TerminalSolver.SolverListener listener, AppCompatActivity activity){
        for(String word : words){
            wordsLeftDataSet.add(new EliminationItem(word));
        }

        solver = new TerminalSolver(words);
        solver.setSolverListener(listener);
        this.activity = activity;
    }

    private void onViewHolderClicked(int position){
        LikenessDialogFragment dialogFragment = new LikenessDialogFragment();
        Bundle args = new Bundle();
        args.putInt(LikenessDialogFragment.BUNDLE_KEY_LIKENESS, solver.getLikeness());
        args.putString(LikenessDialogFragment.BUNDLE_KEY_WORD, getEliminationFromScaledIndex(position).getWord());
        args.putInt(LikenessDialogFragment.BUNDLE_KEY_WORD_INDEX, position);
        dialogFragment.setArguments(args);
        dialogFragment.show(activity.getSupportFragmentManager(), "Likeness");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final EliminationItem item = getEliminationFromScaledIndex(position);

        switch (item.getViewType()){
            case WORD:
                ((WordViewHolder)holder).bind(item.getWord());
                break;
            case FILTER:
                ((FilterWordViewHolder) holder).bind(item.getWord(), item.getLikeness());
                break;
            case ELIMINATED:
                ((EliminatedWordViewHolder) holder).bind(item.getWord());
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType){
            case WORD:
                viewHolder = new WordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.elim_row_word_left, parent, false));
                break;
            case FILTER:
                viewHolder = new FilterWordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.elim_row_filter_word, parent, false));
                break;
            case ELIMINATED:
                viewHolder = new EliminatedWordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.elim_row_eliminated_word, parent, false));
                break;
            default:
                viewHolder = null;
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return getEliminationFromScaledIndex(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return wordsLeftDataSet.size() + filterWordsDataSet.size() + eliminatedWordsDataSet.size();
    }

    public TerminalSolver getSolver(){
        return this.solver;
    }

    // returns Elimination Item from one of:
    // 1. wordsLeftDataSet
    // 2. filterWordsDataSet
    // 3. eliminatedWordsDataSet
    // assumes index is in range: [0, sumLength(datasets) - 1]
    public EliminationItem getEliminationFromScaledIndex(int index) {
        if (index < wordsLeftDataSet.size()){
            return wordsLeftDataSet.get(index);
        } else {
            index -= wordsLeftDataSet.size();
            if(index < filterWordsDataSet.size()){
                return filterWordsDataSet.get(index);
            } else {
                index -= filterWordsDataSet.size();
                return eliminatedWordsDataSet.get(index);
            }
        }
    }

    // Called from parent activity
    @Override
    public void onLikenessConfirmed(int wordIndex, int likeness) {
        // remove from word list
        String word = getEliminationFromScaledIndex(wordIndex).getWord();
        wordsLeftDataSet.remove(wordIndex);
        notifyItemRemoved(wordIndex);

        solver.applyFilter(new WordFilter(word, likeness));
    }

    // Called from parent activity
    @Override
    public void onTerminalSolverFinished(String solvedWord) {

    }

    // Called from parent activity
    // removedWords = words need to restore
    // filter = word need to restore
    @Override
    public void onUndoApplied(List<String> removedWords, WordFilter filter) {
        // Restore filter
        // will need this index for notifyItemRemoved()
        int filterIndex = filterWordsDataSet.indexOf(new EliminationItem(FILTER, filter.getWord(), filter.getLikeness()));
        filterWordsDataSet.remove(filterIndex);
        notifyItemRemoved(wordsLeftDataSet.size() + filterIndex);

        // add it back to the normal word list
        wordsLeftDataSet.add(new EliminationItem(filter.getWord()));
        notifyItemInserted(wordsLeftDataSet.size() - 1);

        // restore removed words
        for(String removedWord : removedWords){
            eliminatedWordsDataSet.remove(new EliminationItem(ELIMINATED, removedWord));
            wordsLeftDataSet.add(new EliminationItem(removedWord));
        }

        notifyItemRangeChanged(0, wordsLeftDataSet.size());
        notifyItemRangeChanged(wordsLeftDataSet.size() + filterWordsDataSet.size(), eliminatedWordsDataSet.size());
    }

    // Called from parent activity
    @Override
    public void onFilterApplied(List<String> removedWords, WordFilter filter) {
        // update filter list
        EliminationItem item = new EliminationItem(FILTER, filter.getWord(), filter.getLikeness());
        filterWordsDataSet.add(item);
        notifyItemChanged(wordsLeftDataSet.size() + filterWordsDataSet.size() - 1);

        Iterator<EliminationItem> itemIterator = wordsLeftDataSet.iterator();
        int index = 0;
        while(itemIterator.hasNext()){
            EliminationItem currentItem = itemIterator.next();
            if(removedWords.contains(currentItem.getWord())) {
                itemIterator.remove();
                notifyItemRemoved(index);
                currentItem.setViewType(ELIMINATED);
                eliminatedWordsDataSet.add(currentItem);
                notifyItemInserted(getItemCount() - 1);
            } else {
                index++;
            }
        }
    }

    // Called from parent activity
    @Override
    public void onRestarted(List<String> words) {
        wordsLeftDataSet.clear();
        eliminatedWordsDataSet.clear();
        filterWordsDataSet.clear();

        for(String word : words){
            wordsLeftDataSet.add(new EliminationItem(word));
        }

        notifyDataSetChanged();
    }

    // Called from parent activity
    @Override
    public void onCancel() {

    }
}
