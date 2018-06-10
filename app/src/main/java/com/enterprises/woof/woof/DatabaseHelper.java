package com.enterprises.woof.woof;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "clients.db";
    private static final String TABLE_NAME = "clients";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DOG_NAME = "dogName";
    private static final String COLUMN_DOG_BREED = "breed";
    private static final String TABLE_NAME_A = "appointments";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table clients " +
            "(id integer primary key not null , " +
            "name text not null , " +
            "email text not null , " +
            "password text not null , " +
            "dogName text not null , " +
            "breed text) ";

    private static final String TABLE_CREATE_A = "create table appointments " +
            "(id integer primary key not null , " +
            "email text not null, " +
            "title text not null , " +
            "date text not null , " +
            "time text not null)";


    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE_A);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        String query1 = "DROP TABLE IF EXISTS " + TABLE_NAME_A;
        db.execSQL(query);
        db.execSQL(query1);
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
        values.put(COLUMN_DOG_BREED, c.getDogBreed());

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

    public String searchDogBreed(String email) {
        db = this.getReadableDatabase();
        String query = "select email, breed from " + TABLE_NAME;
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

    public ArrayList<AppointmentObject> getAppointments(String email) {
        ArrayList<AppointmentObject> appointments = new ArrayList<>();
        db = this.getReadableDatabase();
        String query = "select email, title, date, time from " + TABLE_NAME_A;
        Cursor cursor = db.rawQuery(query, null);
        String a;
        String title, date, time;
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);

                if(a.equals(email)) {
                    title = cursor.getString(1);
                    date = cursor.getString(2);
                    time = cursor.getString(3);
                    AppointmentObject currentAppointment = new AppointmentObject(title, date, time);
                    appointments.add(currentAppointment);
                }
            }while(cursor.moveToNext());
        }
        return appointments;
    }

    public void insertAppointment(AppointmentObject c, Context context) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from clients";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        CurrentUser user = new CurrentUser(context);

        values.put(COLUMN_ID, count);
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_TITLE, c.getTitle());
        values.put(COLUMN_DATE, c.getDate());
        values.put(COLUMN_TIME, c.getTime());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


}