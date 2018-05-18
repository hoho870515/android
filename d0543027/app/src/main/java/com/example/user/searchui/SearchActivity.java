package com.example.user.searchui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends ListActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spin);
        //建立一個ArrayAdapter物件，並放置下拉選單的內容

        String[] str = {"","台中市","彰化縣","南投縣","雲林縣","苗栗縣"};
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,str);
        //設定下拉選單的樣式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //設定項目被選取之後的動作
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            ListView listview = (ListView) findViewById(android.R.id.list);
            public void onItemSelected(AdapterView adapterView, View view, int position, long id){
                switch(position){
                    case 0:
                        ArrayList<String> clear = new ArrayList<>();
                        clear.add(" ");
                        clear.add(" ");
                        clear.add(" ");
                        clear.add(" ");
                        clear.add(" ");
                        ArrayAdapter arrayAdapterClear = new ArrayAdapter(SearchActivity.this,
                                android.R.layout.simple_expandable_list_item_1,clear);
                        listview.setAdapter(arrayAdapterClear);
                        break;
                    case 1:
                        ArrayList<String> str = new ArrayList<String>();
                        str.add("搜尋結果1");
                        str.add("搜尋結果2");
                        str.add("搜尋結果3");
                        str.add("搜尋結果4");
                        str.add("搜尋結果5");
                        ArrayAdapter arrayAdapter = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1, str);
                        listview.setAdapter(arrayAdapter);
                        break;
                    case 2:
                        ArrayList<String> str1 = new ArrayList<String>();
                        str1.add("搜尋結果1");
                        str1.add("搜尋結果2");
                        str1.add("搜尋結果3");
                        str1.add("搜尋結果4");
                        str1.add("搜尋結果5");
                        ArrayAdapter arrayAdapter1 = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1, str1);
                        listview.setAdapter(arrayAdapter1);
                        break;
                    case 3:
                        ArrayList<String> str2 = new ArrayList<String>();
                        str2.add("搜尋結果1");
                        str2.add("搜尋結果2");
                        str2.add("搜尋結果3");
                        str2.add("搜尋結果4");
                        str2.add("搜尋結果5");
                        ArrayAdapter arrayAdapter2 = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1, str2);
                        listview.setAdapter(arrayAdapter2);
                        break;
                    case 4:
                        ArrayList<String> str3 = new ArrayList<String>();
                        str3.add("搜尋結果1");
                        str3.add("搜尋結果2");
                        str3.add("搜尋結果3");
                        str3.add("搜尋結果4");
                        str3.add("搜尋結果5");
                        ArrayAdapter arrayAdapter3 = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1, str3);
                        listview.setAdapter(arrayAdapter3);
                        break;
                    case 5:
                        ArrayList<String> str4 = new ArrayList<String>();
                        str4.add("搜尋結果1");
                        str4.add("搜尋結果2");
                        str4.add("搜尋結果3");
                        str4.add("搜尋結果4");
                        str4.add("搜尋結果5");
                        ArrayAdapter arrayAdapter4 = new ArrayAdapter(SearchActivity.this,android.R.layout.simple_list_item_1, str4);
                        listview.setAdapter(arrayAdapter4);
                        break;
                }

            }
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(SearchActivity.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
