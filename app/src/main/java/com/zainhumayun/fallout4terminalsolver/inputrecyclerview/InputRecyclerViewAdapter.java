package com.zainhumayun.fallout4terminalsolver.inputrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.zainhumayun.fallout4terminalsolver.R;
import java.util.List;

public class InputRecyclerViewAdapter extends RecyclerView.Adapter<InputRecyclerViewAdapter.ViewHolder> implements ImageButton.OnClickListener {

    private int count = 0;

    private List<StringInputItem> dataSet = null;

    public static class ViewHolder extends RecyclerView.ViewHolder implements TextWatcher {
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
            input.addTextChangedListener(this);
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
    }

    public InputRecyclerViewAdapter(List<StringInputItem> dataSet){
        this.dataSet = dataSet;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position, this, dataSet.get(position));
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

    }

    public void addRow(){
        dataSet.add(new StringInputItem(""));
        notifyDataSetChanged();
    }

    public boolean shouldAddAnotherRow(){
        return dataSet != null && (dataSet.isEmpty() || !dataSet.get(dataSet.size() - 1).getString().equals(""));
    }
}
