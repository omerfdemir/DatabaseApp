package com.example.omerf.databaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText book_name = (EditText) findViewById(R.id.et_book_name);
        EditText author_name = (EditText) findViewById(R.id.et_author_name);
        EditText total_page = (EditText) findViewById(R.id.et_total_page);
        EditText start_date = (EditText) findViewById(R.id.et_start_date);
        EditText finish_date = (EditText) findViewById(R.id.et_finish_date);
        Button button_add = (Button) findViewById(R.id.button_add);
        Button button_delete = (Button) findViewById(R.id.button_delete);
        Button button_change = (Button) findViewById(R.id.button_change);
        TextView tv1 = (TextView) findViewById(R.id.tv1);


        //EditText'ten aldıklarımızı tanımlayalım
        final String bookname,authorname;
        final int totalpage;
        Date startdate,finishdate;
        bookname = book_name.getText().toString();
        authorname = author_name.getText().toString();
      //  totalpage = Integer.parseInt(total_page.getText().toString());
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.addBook(bookname,authorname);
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });







    }
}

