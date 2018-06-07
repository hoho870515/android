package com.example.bonso.txt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class New extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Spinner placeSpin = findViewById(R.id.placeSpinner);
        final String[] lunch = {"台中市","彰化縣","南投縣","雲林縣","苗栗縣"};
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(New.this, android.R.layout.simple_spinner_dropdown_item, lunch);
        placeSpin.setAdapter(lunchList);
    }
}
