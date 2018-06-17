package com.example.user.myapplication2;

import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AnalogClock;
import android.widget.DigitalClock;

public class ClockActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cclock);

        DigitalClock txtClock = (DigitalClock) findViewById(R.id.txtClock);
        AnalogClock alclock = (AnalogClock) findViewById(R.id.alClock);
    }
}
