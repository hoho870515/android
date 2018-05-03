package com.example.bonso.mytxt;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Txt extends AppCompatActivity {
    private FloatingActionButton plus_touch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt);
    }

    public void setPlus_touch(FloatingActionButton plus_touch) {
        this.plus_touch = plus_touch;
    }

    public FloatingActionButton getPlus_touch() {
        return plus_touch;
    }
}
