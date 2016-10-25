package com.zainhumayun.fallout4terminalsolver.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CondensedButton extends Button {
    private static final float ALPHA_ENABLED = 1f;
    private static final float ALPHA_DISABLED = 0.2f;

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

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setAlpha(enabled ? ALPHA_ENABLED : ALPHA_DISABLED);
    }
}
