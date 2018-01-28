package com.zainhumayun.fallout4terminalsolver.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class CondensedEditText extends EditText {
    public CondensedEditText(Context context) {
        super(context);
    }

    public CondensedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public CondensedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context);
    }

    private void setCustomFont(Context context){
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "condensed.ttf");
        setTypeface(tf);
    }
}
