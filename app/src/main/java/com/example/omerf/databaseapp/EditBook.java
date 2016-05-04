package com.example.omerf.databaseapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by omerf on 28.04.2016.
 */
public class EditBook extends Activity{
    Button button_change;
    EditText et_book_name,et_author_name,et_total_page,et_start_date,et_finish_date;
    int book_id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_edit);


        button_change = (Button)findViewById(R.id.button1);
        et_book_name = (EditText)findViewById(R.id.ch_et_book_name);
        et_author_name = (EditText)findViewById(R.id.ch_et_author_name);
        et_total_page = (EditText)findViewById(R.id.ch_et_total_page);
        et_start_date = (EditText)findViewById(R.id.ch_et_start_date);
        et_finish_date = (EditText)findViewById(R.id.ch_et_finish_date);


        final Intent intent = getIntent();
        book_id = intent.getIntExtra("book_id", 1);

        Database book_db = new Database(getApplicationContext());
        HashMap<String, String> map = book_db.bookDetails(book_id);

        et_book_name.setText(map.get("book_name"));
        et_author_name.setText(map.get("author_name"));
        et_total_page.setText(map.get("total_page"));
        et_start_date.setText(map.get("start_date"));
        et_finish_date.setText(map.get("finish_date"));

        button_change.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String name,author,totalpage,startdate,finishdate;
                name = et_book_name.getText().toString();
                author = et_author_name.getText().toString();
                totalpage = et_total_page.getText().toString();
                startdate = et_start_date.getText().toString();
                finishdate = et_finish_date.getText().toString();
                if(name.matches("") || author.matches("") || totalpage.matches("") || startdate.matches("")  || finishdate.matches("")  ){
                    Toast.makeText(getApplicationContext(), "Please fill all lines", Toast.LENGTH_LONG).show();
                }else{
                    Database book_db = new Database(getApplicationContext());
                    book_db.editBook(name,author,totalpage,startdate,finishdate,book_id);//g�nderdi�imiz id li kitab�n de�perlerini g�ncelledik.
                    book_db.close();
                    Toast.makeText(getApplicationContext(), "Book has been editted succesfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MyLibrary.class);
                    startActivity(intent);
                    finish();
                }


            }
        });


    }

    }





