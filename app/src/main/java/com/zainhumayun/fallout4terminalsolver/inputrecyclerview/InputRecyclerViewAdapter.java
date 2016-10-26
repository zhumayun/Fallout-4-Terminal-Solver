package com.zainhumayun.fallout4terminalsolver.inputrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.zainhumayun.fallout4terminalsolver.R;
import java.util.List;

public class InputRecyclerViewAdapter extends RecyclerView.Adapter<InputRecyclerViewAdapter.ViewHolder> implements ImageButton.OnClickListener {

    private List<StringInputItem> dataSet = null;

    public class ViewHolder extends RecyclerView.ViewHolder implements TextWatcher {
        private EditText input;
        private ImageButton closeButton;
        private StringInputItem inputString;

        public ViewHolder(View itemView) {
            super(itemView);
            input = (EditText) itemView.findViewById(R.id.input_row_edit_text);
            closeButton = (ImageButton) itemView.findViewById(R.id.input_row_close_button);
        }

        public void bind(int position, View.OnClickListener listener, final StringInputItem inputString){
            closeButton.setOnClickListener(listener);
            closeButton.setTag(position);
            this.inputString = inputString;
            input.setText(inputString.getString());
            input.addTextChangedListener(this);
            input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId == EditorInfo.IME_ACTION_NEXT){
                        InputRecyclerViewAdapter.this.addRow();
                        return true;
                    }
                    return false;
                }
            });
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            inputString.setString(s.toString()); // updates reference in outside adapter
        }

        public void focus(){
            input.requestFocus();
        }
    }

    public InputRecyclerViewAdapter(List<StringInputItem> dataSet){
        this.dataSet = dataSet;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position, this, dataSet.get(position));

        if(position == 0) {
            holder.focus();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.input_row, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public int getItemCount() {
        return dataSet == null ?  0 : dataSet.size();
    }

    @Override
    public void onClick(View v) {
        final int viewHolderPosition = (int) v.getTag();
        removeRow(viewHolderPosition);
    }

    private void removeRow(int position){
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void addRow(){
        if(!shouldAddAnotherRow()) return;

        dataSet.add(0, new StringInputItem(""));
        notifyItemInserted(0);
    }

    public boolean shouldAddAnotherRow(){
        return dataSet != null && (dataSet.isEmpty() || !dataSet.get(dataSet.size() - 1).getString().equals(""));
    }

    public boolean isDataValid(){
        if(dataSet == null || dataSet.size() == 0)
            return false;

        final int EXACT_WORD_SIZE = dataSet.get(0).getString().trim().length();

        for(StringInputItem item : dataSet){
            if(item.getString().trim().length() != EXACT_WORD_SIZE)
                return false;
        }

        return EXACT_WORD_SIZE > 0;
    }
}
