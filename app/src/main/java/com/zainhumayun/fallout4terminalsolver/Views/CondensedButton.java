package com.zainhumayun.fallout4terminalsolver.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CondensedButton extends Button {
    public CondensedButton(Context context){
        super(context);
    }

    public CondensedButton(Context context, AttributeSet set){
        super(context, set);
        setCustomFont(context);
    }

    public CondensedButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context);
    }

    private void setCustomFont(Context context){
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "condensed.ttf");
        setTypeface(tf);
    }
}
