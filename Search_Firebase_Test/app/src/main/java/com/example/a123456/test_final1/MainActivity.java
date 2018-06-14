package com.example.a123456.test_final1;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

public class MainActivity extends AppCompatActivity {
    private HotelArrayAdapter adapter = null;

    private static final int LIST_HotelS = 1;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LIST_HotelS: {
                    List<TData> hotels = (List<TData>) msg.obj;
                    refreshHotelList(hotels);
                    break;
                }
            }
        }
    };

    private void refreshHotelList(List<TData> Hotels) {
        adapter.clear();
        adapter.addAll(Hotels);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView lvHotels = (ListView) findViewById(R.id.list);
        Spinner spinner = findViewById(R.id.spin);

        final String[] str = {"","test","test2"};
        ArrayAdapter<String> sAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,str);
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sAdapter);

        adapter = new HotelArrayAdapter(this, new ArrayList<TData>());
        lvHotels.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 1:
                        getHotelsFromFirebase("test");
                        lvHotels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch(position){
                                    case 0:
                                        Toast.makeText(MainActivity.this,"You click first one",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        break;
                    case 2:
                        getHotelsFromFirebase("test2");
                        lvHotels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                switch(position){
                                    case 0:
                                        Toast.makeText(MainActivity.this,"You click second one",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       // getHotelsFromFirebase("test");

        //getHotelsFromFirebase("test2");
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
            msg.what = LIST_HotelS;
            handler.sendMessage(msg);
        }
    }

    private void getHotelsFromFirebase(String sData) {
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
    class HotelArrayAdapter extends ArrayAdapter<TData> {
        Context context;

        public HotelArrayAdapter(Context context, List<TData> items) {
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
