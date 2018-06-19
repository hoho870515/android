package com.example.hoho.hw51;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button btn_Notification;
    EditText et_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Notification = findViewById(R.id.btn_Notification);
        btn_Notification.setOnClickListener(rec_notification);
        et_input = findViewById(R.id.et_Input);
    }

    private View.OnClickListener rec_notification = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction("hoho.NOTIFICATION");
            String msg = et_input.getEditableText().toString();
            intent.putExtra("KEY_MSG",msg);
            sendBroadcast(intent);
        }
    };
}
