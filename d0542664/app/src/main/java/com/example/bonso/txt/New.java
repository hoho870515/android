package com.example.bonso.txt;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class New extends AppCompatActivity {

    EditText title, note;
    ArrayList<String> titlelist;
    SQLiteDatabase db;
    int notepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Spinner placeSpin = findViewById(R.id.placeSpinner);
        final String[] lunch = {"台中市","彰化縣","南投縣","雲林縣","苗栗縣"};
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(this,R.layout.myspinnertxt,lunch);
        placeSpin.setAdapter(lunchList);

        title = (EditText)findViewById(R.id.Title);
        note = (EditText)findViewById(R.id.Note);

        Intent intent = getIntent();
        notepos = intent.getIntExtra("NOTEPOS", -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBOpenHelper openhelper = new DBOpenHelper(this);
        db = openhelper.getWritableDatabase();

        titlelist = NoteDB.getTitleList(db);

        if (notepos != -1) {
            String printfTitle = titlelist.get(notepos);
            title.setText(printfTitle);
            note.setText(NoteDB.getBody(db,printfTitle));
        } else {
            title.setText("");
            note.setText("");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        String printfTitle = title.getText().toString();
        if (printfTitle.length() == 0) {
            Toast.makeText(this, "標題不能為空白，便條無儲存", Toast.LENGTH_SHORT).show();
        } else {
            NoteDB.addNote(db, title.getText().toString(),
                    note.getText().toString());
        }
    }

    boolean isTitleExist(String title) {
        for (int i = 0; i < titlelist.size(); i++)
            if (title.equalsIgnoreCase(titlelist.get(i)))
                return true;
        return false;
    }
}
