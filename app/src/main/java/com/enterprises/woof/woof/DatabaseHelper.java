package com.enterprises.woof.woof;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "clients.db";
    private static final String TABLE_NAME = "clients";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DOG_NAME = "dogName";

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table clients (id integer primary key not null , " +
            " name text not null , email text not null , password text not null , dogName text not null) ";

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertClient(Client c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from clients";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASSWORD, c.getPassword());
        values.put(COLUMN_DOG_NAME, c.getDogName());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public String searchPassword(String email) {
        db = this.getReadableDatabase();
        String query = "select email, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "Not Found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if(a.equals(email)) {
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }


    public String searchName(String email) {
        db = this.getReadableDatabase();
        String query = "select email, name from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "Not Found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if(a.equals(email)) {
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }

    public String searchDog(String email) {
        db = this.getReadableDatabase();
        String query = "select email, dogName from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "Not Found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if(a.equals(email)) {
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }

    public List<String> getEmails() {
        ArrayList<String> emails = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select email from " + TABLE_NAME;
        Cursor cursor = database.rawQuery(query, null);
        while(!cursor.moveToNext()) {
            String email = cursor.getString(0);
            emails.add(email);
        }
        return emails;
    }


}
