package com.zainhumayun.fallout4terminalsolver.activities;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zainhumayun.fallout4terminalsolver.R;

public class AboutActivity extends HideActionBarActivity {
    private LinearLayout sectionContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        sectionContainer = (LinearLayout) findViewById(R.id.about_section_container);
        addSection(R.string.about_heading_features, R.string.about_features_content);
    }

    private void addSection(@StringRes int headerText, @StringRes int contentText){
        View rootView = LayoutInflater.from(this).inflate(R.layout.about_section, sectionContainer, false);

        TextView header = (TextView) rootView.findViewById(R.id.about_section_heading);
        TextView content = (TextView) rootView.findViewById(R.id.about_section_content);

        header.setText(headerText);
        content.setText(contentText);

        sectionContainer.addView(rootView);
    }
}
