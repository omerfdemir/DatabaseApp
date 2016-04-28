package com.example.omerf.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by omerf on 28.04.2016.
 */
public class Database extends SQLiteOpenHelper {
    //DB Name
    private static final String DB_NAME = "Books_DB";

    //DB VERSION
    private static final int DB_VERSION = 1;
    //TABLE
    private static String TABLE_NAME = "books";
    private static String BOOK_ID = "book_id ";
    private static String BOOK_NAME = "book_name ";
    private static String AUTHOR_NAME = "author_name ";
    private static String TOTAL_PAGE = "total_page ";
    private static String START_DATE = "start_date ";
    private static String FINISH_DATE = "finish_date ";
    public static Object addBook;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //CREATE TABLE


    //Kitap Ekleme Methodu
    public void addBook(String bookname, String authorname, String totalpage, String startdate, String finishdate) {
        SQLiteDatabase book_db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK_NAME, bookname);
        values.put(AUTHOR_NAME, authorname);
        values.put(TOTAL_PAGE, totalpage);
        values.put(START_DATE, startdate);
        values.put(FINISH_DATE, finishdate);
        //Verileri insert ediyoruz
        book_db.insert(TABLE_NAME,null,values);
        //DB'yi kapatıyoruz
        book_db.close();
    }

    //Kitap Silme Methodu
    public void deleteBook(int book_id) {
        SQLiteDatabase book_db = this.getWritableDatabase();
        book_db.delete(TABLE_NAME, BOOK_ID + " = ? ",
                new String[]{String.valueOf(book_id)});
        book_db.close();
    }

    //Kitap ID si kullanarak kitapları göstermek için kullanılır
    public HashMap<String, String> bookDetails(int book_id) {
        HashMap<String, String> book = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + "WHERE book_id = " + book_id;
        SQLiteDatabase book_db = getReadableDatabase();
        Cursor cursor = book_db.rawQuery(selectQuery, null);
        //Move the first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            book.put(BOOK_NAME, cursor.getString(1));
            book.put(AUTHOR_NAME, cursor.getString(2));
            book.put(TOTAL_PAGE, cursor.getString(3));
            book.put(START_DATE, cursor.getString(4));
            book.put(FINISH_DATE, cursor.getString(5));
        }
        cursor.close();
        book_db.close();
        return book;
    }

    //Tüm kitapları göstermek için kullanılır
    public ArrayList<HashMap<String, String>> allBooks() {
        SQLiteDatabase book_db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = book_db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> bookList = new ArrayList<HashMap<String, String>>();
        //Tüm kitapları döngüyle getirir

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }
                bookList.add(map);
            } while (cursor.moveToNext());
        }
        book_db.close();
        return bookList;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + "(" +
                BOOK_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                BOOK_NAME + "TEXT ," +
                AUTHOR_NAME + "TEXT," +
                TOTAL_PAGE + "TEXT," +
                START_DATE + "DATE," +
                FINISH_DATE + "DATE" + ")";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}


