package com.example.user.myapplication2;

import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.content.Intent;

public class ClockActivity extends AppCompatActivity {

    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cclock);

        DigitalClock txtClock = (DigitalClock) findViewById(R.id.txtClock);
        AnalogClock alclock = (AnalogClock) findViewById(R.id.alClock);

        home = (Button)findViewById(R.id.button＿gohome);
        home.setOnClickListener(gohome);
    }

    private View.OnClickListener gohome = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final int ACTIVITY_CLOCK = 1;
            Intent intent = new Intent();
            intent.setClass(ClockActivity.this,
                    MainActivity.class);
            startActivityForResult(intent,ACTIVITY_CLOCK);
        }
    };
}
