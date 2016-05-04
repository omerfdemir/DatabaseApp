package com.example.omerf.databaseapp;

import android.content.Intent;
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
import android.widget.Toast;

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
        final ArrayList<HashMap<String, String>> book;

        final EditText book_name = (EditText) findViewById(R.id.et_book_name);
        final EditText author_name = (EditText) findViewById(R.id.et_author_name);
        final EditText total_page = (EditText) findViewById(R.id.et_total_page);
        final EditText start_date = (EditText) findViewById(R.id.et_start_date);
        final EditText finish_date = (EditText) findViewById(R.id.et_finish_date);
        //total_page.setInputType(InputType.TYPE_CLASS_NUMBER);
        Button button_add = (Button) findViewById(R.id.button_add);
        Button button_delete = (Button) findViewById(R.id.button_delete);
        Button button_change = (Button) findViewById(R.id.button_change);



        //EditText'ten aldıklarımızı tanımlayalım

        //int totalpage_int = 0;
        //totalpage_int = Integer.valueOf(totalpage).intValue();
        //final int total_page_int = totalpage_int;

        //  totalpage = Integer.parseInt(total_page.getText().toString());


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database book_db = new Database(getApplicationContext());
                final String bookname = book_name.getText().toString();
                final String authorname = author_name.getText().toString();
                final String totalpage = total_page.getText().toString();
                final String startdate = start_date.getText().toString();
                final String finishdate = finish_date.getText().toString();
                book_db.addBook(bookname, authorname, totalpage, startdate, finishdate);
                Toast.makeText(getApplicationContext(),"Book has been added succesfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MyLibrary.class);
                startActivity(intent);

            }
        });
    }


    public void onResume(){
        super.onResume();


    }






    }


