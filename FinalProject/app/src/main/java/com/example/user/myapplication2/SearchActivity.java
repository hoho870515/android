package com.example.user.myapplication2;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends ListActivity{

    private DataArrayAdapter adapter = null;

    private static final int LIST_DATAS = 1;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LIST_DATAS: {
                    List<TData> datas = (List<TData>) msg.obj;
                    refreshList(datas);
                    break;
                }
            }
        }
    };

    private void refreshList(List<TData> Datas) {
        adapter.clear();
        adapter.addAll(Datas);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //final ListView listview = (ListView) findViewById(android.R.id.list);
        final String[] str = {"","台中市","彰化縣","南投縣","雲林縣","苗栗縣"};
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(this,R.layout.myspinner,str);
        //設定下拉選單的樣式
        sAdapter.setDropDownViewResource(R.layout.myspinner);
        Spinner spinner = (Spinner) findViewById(R.id.spin);
        spinner.setAdapter(sAdapter);
        //設定項目被選取之後的動作
        //adapter = new DataArrayAdapter(this, new ArrayList<TData>());
        //listview.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView adapterView, View view, final int position, long id){
                switch(position){
                    case 0:
                        ListView lv_clear = findViewById(android.R.id.list);
                        ArrayList<String> clear = new ArrayList<>();
                        clear.add(" ");
                        clear.add(" ");
                        clear.add(" ");
                        clear.add(" ");
                        clear.add(" ");
                        ArrayAdapter<String> arrayAdapterClear = new ArrayAdapter<String>(SearchActivity.this,
                                android.R.layout.simple_expandable_list_item_1,clear);
                        lv_clear.setAdapter(arrayAdapterClear);
                        lv_clear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                switch(i) {
                                    case 0:
                                    Intent intent = new Intent();
                                    intent.setClass(SearchActivity.this, SearchActivity.class);
                                    startActivity(intent);
                                    break;
                                    case 1:
                                        Intent intent1 = new Intent();
                                        intent1.setClass(SearchActivity.this,SearchActivity.class);
                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent();
                                        intent2.setClass(SearchActivity.this, SearchActivity.class);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        Intent intent3 = new Intent();
                                        intent3.setClass(SearchActivity.this, SearchActivity.class);
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        Intent intent4 = new Intent();
                                        intent4.setClass(SearchActivity.this, SearchActivity.class);
                                        startActivity(intent4);
                                        break;
                                }
                            }
                        });
                        break;
                    case 1:
                        ListView listview = (ListView) findViewById(android.R.id.list);
                        adapter = new DataArrayAdapter(SearchActivity.this, new ArrayList<TData>());
                        listview.setAdapter(adapter);

                        getDatasFromFirebase("Taichung");

                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:
                                        Intent intent = new Intent();
                                        intent.setClass(SearchActivity.this,taichung_b.class);
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        Intent intent1 = new Intent();
                                        intent1.setClass(SearchActivity.this,taichung_a.class);
                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent();
                                        intent2.setClass(SearchActivity.this,taichung_c.class);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        Intent intent3 = new Intent();
                                        intent3.setClass(SearchActivity.this,taichung_e.class);
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        Intent intent4 = new Intent();
                                        intent4.setClass(SearchActivity.this,taichung_d.class);
                                        startActivity(intent4);
                                        break;
                                }
                            }
                        });
                        break;
                    case 2:
                        ListView listview1 = (ListView) findViewById(android.R.id.list);
                        adapter = new DataArrayAdapter(SearchActivity.this, new ArrayList<TData>());
                        listview1.setAdapter(adapter);

                        getDatasFromFirebase("Changhua");

                        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){

                                    case 0:
                                        Intent intent = new Intent();
                                        intent.setClass(SearchActivity.this,chunghua_a.class);
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        Intent intent1 = new Intent();
                                        intent1.setClass(SearchActivity.this,chunghua_b.class);
                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent();
                                        intent2.setClass(SearchActivity.this,chunghua_c.class);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        Intent intent3 = new Intent();
                                        intent3.setClass(SearchActivity.this,chunghua_d.class);
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        Intent intent4 = new Intent();
                                        intent4.setClass(SearchActivity.this,chunghua_e.class);
                                        startActivity(intent4);
                                        break;
                                }
                            }
                        });

                        break;
                    case 3:
                        ListView listview2 = (ListView) findViewById(android.R.id.list);
                        adapter = new DataArrayAdapter(SearchActivity.this, new ArrayList<TData>());
                        listview2.setAdapter(adapter);

                        getDatasFromFirebase("Nantou");
                        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){

                                    case 0:
                                        Intent intent = new Intent();
                                        intent.setClass(SearchActivity.this,nantou_a.class);
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        Intent intent1 = new Intent();
                                        intent1.setClass(SearchActivity.this,nantou_b.class);
                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent();
                                        intent2.setClass(SearchActivity.this,nantou_c.class);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        Intent intent3 = new Intent();
                                        intent3.setClass(SearchActivity.this,nantou_d.class);
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        Intent intent4 = new Intent();
                                        intent4.setClass(SearchActivity.this,nantou_e.class);
                                        startActivity(intent4);
                                        break;
                                }
                            }
                        });
                        break;
                    case 4:
                        ListView listview3 = (ListView) findViewById(android.R.id.list);
                        adapter = new DataArrayAdapter(SearchActivity.this, new ArrayList<TData>());
                        listview3.setAdapter(adapter);

                        getDatasFromFirebase("Yunlin");

                        listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){

                                    case 0:
                                        Intent intent = new Intent();
                                        intent.setClass(SearchActivity.this,yuniln_a.class);
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        Intent intent1 = new Intent();
                                        intent1.setClass(SearchActivity.this,yunlin_b.class);
                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent();
                                        intent2.setClass(SearchActivity.this,yunlin_c.class);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        Intent intent3 = new Intent();
                                        intent3.setClass(SearchActivity.this,yunlin_d.class);
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        Intent intent4 = new Intent();
                                        intent4.setClass(SearchActivity.this,yunlin_e.class);
                                        startActivity(intent4);
                                        break;
                                }
                            }
                        });
                        break;
                    case 5:
                        ListView listview4 = (ListView) findViewById(android.R.id.list);
                        adapter = new DataArrayAdapter(SearchActivity.this, new ArrayList<TData>());
                        listview4.setAdapter(adapter);

                        getDatasFromFirebase("Miaoli");
                        listview4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){

                                    case 0:
                                        Intent intent = new Intent();
                                        intent.setClass(SearchActivity.this,miaoli_a.class);
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        Intent intent1 = new Intent();
                                        intent1.setClass(SearchActivity.this,miaoli_b.class);
                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent();
                                        intent2.setClass(SearchActivity.this,miaoli_c.class);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        Intent intent3 = new Intent();
                                        intent3.setClass(SearchActivity.this,miaoli_d.class);
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        Intent intent4 = new Intent();
                                        intent4.setClass(SearchActivity.this,miaoli_e.class);
                                        startActivity(intent4);
                                        break;
                                }
                            }
                        });
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(SearchActivity.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }
        });
    }

    class FirebaseThread extends Thread {

        private DataSnapshot dataSnapshot;

        public FirebaseThread(DataSnapshot dataSnapshot) {
            this.dataSnapshot = dataSnapshot;
        }
        @Override
        public void run() {
            List<TData> lsHotels = new ArrayList<>();
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                DataSnapshot dsName = ds.child("Name");
                DataSnapshot dsAddress = ds.child("Add");

                String tname = (String) dsName.getValue();
                String taddress = (String) dsAddress.getValue();
                Log.v("TEST", tname + ";" + taddress);

                TData data = new TData();
                data.setAddress(taddress);
                data.setName(tname);
                lsHotels.add(data);
            }
            Message msg = new Message();
            msg.obj = lsHotels;
            msg.what = LIST_DATAS;
            handler.sendMessage(msg);
        }
    }

    private void getDatasFromFirebase(String sData) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(sData);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                new FirebaseThread(dataSnapshot).start();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("Hotel", databaseError.getMessage());
            }
        });
    }
    class DataArrayAdapter extends ArrayAdapter<TData> {
        Context context;

        public DataArrayAdapter(Context context, List<TData> items) {
            super(context, 0, items);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            LinearLayout itemlayout = null;
            if (convertView == null) {
                itemlayout = (LinearLayout) inflater.inflate(R.layout.data_items, null);
            } else {
                itemlayout = (LinearLayout) convertView;
            }
            TData item = (TData) getItem(position);
            TextView tvName = (TextView) itemlayout.findViewById(R.id.tv_name);
            tvName.setText(item.getName());
            TextView tvAddress = (TextView) itemlayout.findViewById(R.id.tv_address);
            tvAddress.setText(item.getAddress());
            return itemlayout;
        }
    }
    class TData{
        String name;
        String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}

