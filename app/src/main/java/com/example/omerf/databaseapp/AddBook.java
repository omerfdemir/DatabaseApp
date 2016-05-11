package com.example.omerf.databaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.add_book);
        ArrayAdapter adapter;
        final ArrayList<HashMap<String, String>> book;

        final EditText book_name = (EditText) findViewById(R.id.et_book_name);
        final EditText author_name = (EditText) findViewById(R.id.et_author_name);
        final EditText total_page = (EditText) findViewById(R.id.et_total_page);
        final EditText start_date = (EditText) findViewById(R.id.et_start_date);
        final EditText finish_date = (EditText) findViewById(R.id.et_finish_date);
        final EditText book_content = (EditText) findViewById(R.id.et_book_content);
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
                final String bookcontent = book_content.getText().toString();
                book_db.addBook(bookname, authorname, totalpage, startdate, finishdate,bookcontent);
                Toast.makeText(getApplicationContext(),"Book has been added succesfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),MyLibrary.class);
                startActivity(intent);

            }
        });
    }


    public void onResume(){
        super.onResume();


    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions,menu);
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
        Intent intent = new Intent(AddBook.this, AboutMe.class);
        startActivity(intent);
    }
    private void Contactt(){
        Intent intent = new Intent(AddBook.this,Contact.class);
        startActivity(intent);
    }
    }









