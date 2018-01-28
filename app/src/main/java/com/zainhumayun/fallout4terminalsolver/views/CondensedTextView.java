package com.zainhumayun.fallout4terminalsolver.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created to Apply font condensed.tff
 **/
public class CondensedTextView extends TextView {
    public CondensedTextView(Context context) {
        super(context);
    }

    public CondensedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public CondensedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context);
    }

    private void setCustomFont(Context context){
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "condensed.ttf");
        setTypeface(tf);
    }
}
