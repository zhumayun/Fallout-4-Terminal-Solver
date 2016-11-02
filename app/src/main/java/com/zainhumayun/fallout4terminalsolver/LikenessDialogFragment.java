package com.zainhumayun.fallout4terminalsolver;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class LikenessDialogFragment extends DialogFragment {
    private DialogListener listener;
    private String word;
    private int maxLikeness = 0;
    private int wordIndex = 0;

    /**
     * Used for Bundle
     **/
    public static final String BUNDLE_KEY_WORD = "WORD";
    public static final String BUNDLE_KEY_LIKENESS = "LIKENESS";
    public static final String BUNDLE_KEY_WORD_INDEX = "INDEX";

    public interface DialogListener {
        void onLikenessConfirmed(int wordIndex, int likeness);
        void onCancel();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        word = getArguments().getString(BUNDLE_KEY_WORD);
        maxLikeness = getArguments().getInt(BUNDLE_KEY_LIKENESS);
        wordIndex = getArguments().getInt(BUNDLE_KEY_WORD_INDEX);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_likeness_input, null);

        final SeekBar bar = (SeekBar) rootView.findViewById(R.id.dialog_likeness_seekbar);
        final TextView counterText = (TextView) rootView.findViewById(R.id.dialog_likeness_counter);

        bar.setMax(maxLikeness);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                counterText.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        builder.setView(rootView)
                .setTitle("Likeness for " + word)
                .setPositiveButton(R.string.alert_done, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onLikenessConfirmed(wordIndex, bar.getProgress());
                    }
                })
                .setNeutralButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onCancel();
                    }
                });

        final AlertDialog dialog = builder.create();

        // change colour of dialog button
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
            }
        });

        return dialog;
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
