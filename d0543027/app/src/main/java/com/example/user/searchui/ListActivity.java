package com.example.user.searchui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent intent = getIntent();
        ListView listview = (ListView) findViewById(R.id.listview);
        //ListView 要顯示的內容
        String[] str = {"新北市","台北市","台中市","台南市","高雄市"};
        //android.R.layout.simple_list_item_1 為內建樣式，還有其他樣式可自行研究
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, str);
        listview.setAdapter(adapter);
    }

}
