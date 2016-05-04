package com.example.omerf.databaseapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by omerf on 28.04.2016.
 */
public class MyLibrary  extends Activity{
    ArrayAdapter<String> adapter;
    String [] book_names;
    int [] book_ids;
    ArrayList<HashMap<String,String>> book_list;
    ListView lv;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_library);
        Button b1 = (Button) findViewById(R.id.add_book);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });



    }
    protected void onResume() {
        super.onResume();

        Database book_db = new Database(getApplicationContext());
        book_list = book_db.allBooks();

        if (book_list.size() == 0) {
            Toast.makeText(getApplicationContext(), "Henüz kitap eklemediniz.\n Lütfen Kitap ekleyiniz", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            book_names = new String[book_list.size()];
            book_ids = new int[book_list.size()];
            for (int i = 0; i < book_list.size(); i++) {
                book_names[i] = book_list.get(i).get("book_name");

                book_ids[i] = Integer.parseInt(book_list.get(i).get("book_id"));
            }
            lv = (ListView) findViewById(R.id.my_library_lv);
            adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.bn_tv, book_names);
            lv.setAdapter(adapter);

        }
        /**lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,View view , int position ,long id) {
                String item = (String) lv.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), BookDetails.class);
                intent.putExtra("book_id", position+1);
                startActivity(intent);

            }
        });*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //Listedeki her hangibir yere t�kland�g�nda t�klanan sat�r�n s�ras�n� al�yoruz.
                //Bu s�ra id arraydeki s�rayla ayn� oldugundan t�klanan sat�rda bulunan kitap�n id sini al�yor ve kitap detaya g�nderiyoruz.
                Intent intent = new Intent(getApplicationContext(), BookDetails.class);
                intent.putExtra("book_id", (int)book_ids[arg2]);
                startActivity(intent);

            }
        });
    }



}
