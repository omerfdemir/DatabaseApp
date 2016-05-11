package com.example.omerf.databaseapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by omerf on 28.04.2016.
 */
public class BookDetails extends AppCompatActivity{
    int book_id;
    Button button_change,button_delete;
    TextView tv_book_name,tv_author_name,tv_total_page,tv_start_date,tv_finish_date,tv_book_content;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        setContentView(R.layout.book_details);

        //ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setTitle("Book List");

        //Buttonları tanımlıyoruz
        Button button_add = (Button) findViewById(R.id.button_add);
        Button button_delete = (Button) findViewById(R.id.button_delete);
        Button button_change = (Button) findViewById(R.id.button_change);

        //TextView'leri tanımlıyoruz
        TextView tv_book_name = (TextView) findViewById(R.id.tv_book_name);
        TextView tv_author_name = (TextView) findViewById(R.id.tv_author_name);
        TextView tv_total_page = (TextView) findViewById(R.id.tv_total_page);
        TextView tv_start_date = (TextView) findViewById(R.id.tv_start_date);
        TextView tv_finish_date = (TextView) findViewById(R.id.tv_finish_date);
        TextView tv_book_content = (TextView) findViewById(R.id.tv_book_content);


        final Intent intent=getIntent();
        book_id = intent.getIntExtra("book_id", 1);

        Database book_db = new Database(getApplicationContext());

        HashMap<String,String> map = book_db.bookDetails(book_id);

        tv_book_name.setText(map.get("book_name"));
        tv_author_name.setText(map.get("author_name"));
        tv_total_page.setText(map.get("total_page"));
        tv_start_date.setText(map.get("start_date"));
        tv_finish_date.setText(map.get("finish_date"));
        tv_book_content.setText(map.get("book_content"));

        //Add butonunu sildim.
      /**  button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });*/
        //Değiştir butonuna basınca yeni intent çağırırız
        button_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditBook.class);
                intent.putExtra("book_id",book_id);
                startActivity(intent);
            }


        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BookDetails.this);
                alertDialog.setTitle("Warning!");
                alertDialog.setTitle("Do you want to delete the book?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Database book_db = new Database(getApplicationContext());
                        book_db.deleteBook(book_id);
                        Toast.makeText(getApplicationContext(),"Book has been deleted succesfully",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MyLibrary.class);
                        startActivity(intent);// Kitabı silip ana sayfaya döndük
                        finish();

                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });
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
        Intent intent = new Intent(BookDetails.this, AboutMe.class);
        startActivity(intent);
    }
    private void Contactt(){
        Intent intent = new Intent(BookDetails.this,Contact.class);
        startActivity(intent);
    }
}
