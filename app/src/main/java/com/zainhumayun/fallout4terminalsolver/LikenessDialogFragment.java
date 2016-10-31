package com.zainhumayun.fallout4terminalsolver;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.SeekBar;

public class LikenessDialogFragment extends DialogFragment {
    private DialogListener listener;
    private String word;
    private int maxLikeness = 0;

    /**
     * Used for Bundle
     **/
    public static final String BUNDLE_KEY_WORD = "WORD";
    public static final String BUNDLE_KEY_LIKENESS = "LIKENESS";

    public interface DialogListener {
        void onLikenessConfirmed(String word, int likeness);
        void onCancel();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        word = getArguments().getString(BUNDLE_KEY_WORD);
        maxLikeness = getArguments().getInt(BUNDLE_KEY_LIKENESS);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_likeness_input, null);

        final SeekBar bar = (SeekBar) rootView.findViewById(R.id.dialog_likeness_seekbar);
        bar.setMax(maxLikeness);

        builder.setView(rootView)
                .setTitle("Likeness for " + word)
                .setPositiveButton("done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onLikenessConfirmed(word, bar.getProgress());
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onCancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (DialogListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement LikenessDialogFragment.DialogListener");
        }
    }
}
