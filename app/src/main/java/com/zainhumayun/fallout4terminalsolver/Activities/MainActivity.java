package com.zainhumayun.fallout4terminalsolver.Activities;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.zainhumayun.fallout4terminalsolver.R;


/**
 * Activity for main screen where the user can select to start solving a terminal
 **/
public class MainActivity extends HideActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = (Button) findViewById(R.id.main_activity_button_start);

        // change colour of start button
        start.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inputActvityIntent = new Intent(MainActivity.this, InputWordsActivity.class);
                startActivity(inputActvityIntent);
            }
        });
    }
}
