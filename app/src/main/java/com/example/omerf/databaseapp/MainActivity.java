package com.example.omerf.databaseapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adapter;
        final ArrayList<HashMap<String, String>> boo;

        EditText book_name = (EditText) findViewById(R.id.et_book_name);
        EditText author_name = (EditText) findViewById(R.id.et_author_name);
        EditText total_page = (EditText) findViewById(R.id.et_total_page);
        EditText start_date = (EditText) findViewById(R.id.et_start_date);
        EditText finish_date = (EditText) findViewById(R.id.et_finish_date);
        //total_page.setInputType(InputType.TYPE_CLASS_NUMBER);
        Button button_add = (Button) findViewById(R.id.button_add);
        Button button_delete = (Button) findViewById(R.id.button_delete);
        Button button_change = (Button) findViewById(R.id.button_change);
        final TextView tv1 = (TextView) findViewById(R.id.tv1);


        //EditText'ten aldıklarımızı tanımlayalım
        final String bookname = book_name.getText().toString();
        final String authorname = author_name.getText().toString();
        final String totalpage = total_page.getText().toString();
        final String startdate = start_date.getText().toString();
        final String finishdate = finish_date.getText().toString();
        //int totalpage_int = 0;
        //totalpage_int = Integer.valueOf(totalpage).intValue();
        //final int total_page_int = totalpage_int;

        //  totalpage = Integer.parseInt(total_page.getText().toString());


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database book_db = new Database(getApplicationContext());
                book_db.addBook(bookname, authorname, totalpage, startdate, finishdate);
                tv1.setText(bookname);
            }
        });
    }


    public void onResume(){
        super.onResume();


    }






    }


