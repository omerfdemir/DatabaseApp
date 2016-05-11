package com.example.omerf.databaseapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by omerf on 28.04.2016.
 */
public class EditBook extends AppCompatActivity {
    Button button_change;
    EditText et_book_name, et_author_name, et_total_page, et_start_date, et_finish_date,et_book_content;
    int book_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_edit);


        button_change = (Button) findViewById(R.id.button1);
        et_book_name = (EditText) findViewById(R.id.ch_et_book_name);
        et_author_name = (EditText) findViewById(R.id.ch_et_author_name);
        et_total_page = (EditText) findViewById(R.id.ch_et_total_page);
        et_start_date = (EditText) findViewById(R.id.ch_et_start_date);
        et_finish_date = (EditText) findViewById(R.id.ch_et_finish_date);
        et_book_content = (EditText) findViewById(R.id.ch_et_book_content);

        final Intent intent = getIntent();
        book_id = intent.getIntExtra("book_id", 1);

        Database book_db = new Database(getApplicationContext());
        HashMap<String, String> map = book_db.bookDetails(book_id);

        et_book_name.setText(map.get("book_name"));
        et_author_name.setText(map.get("author_name"));
        et_total_page.setText(map.get("total_page"));
        et_start_date.setText(map.get("start_date"));
        et_finish_date.setText(map.get("finish_date"));
        et_book_content.setText(map.get("book_content"));

        button_change.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String name, author, totalpage, startdate, finishdate, bookcontent;
                name = et_book_name.getText().toString();
                author = et_author_name.getText().toString();
                totalpage = et_total_page.getText().toString();
                startdate = et_start_date.getText().toString();
                finishdate = et_finish_date.getText().toString();
                bookcontent = et_book_content.getText().toString();
                if (name.matches("") || author.matches("") || totalpage.matches("") || startdate.matches("") || finishdate.matches("") || bookcontent.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all lines", Toast.LENGTH_LONG).show();
                } else {
                    Database book_db = new Database(getApplicationContext());
                    book_db.editBook(name, author, totalpage, startdate, finishdate,bookcontent, book_id);//g�nderdi�imiz id li kitab�n de�perlerini g�ncelledik.
                    book_db.close();
                    Toast.makeText(getApplicationContext(), "Book has been editted succesfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MyLibrary.class);
                    startActivity(intent);
                    finish();
                }


            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_about_me:
                AboutMee();
                return true;
            case R.id.action_contact:
                Contactt();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    private void AboutMee() {
        Intent intent = new Intent(EditBook.this, AboutMe.class);
        startActivity(intent);
    }
    private void Contactt(){
        Intent intent = new Intent(EditBook.this,Contact.class);
        startActivity(intent);
    }
}






