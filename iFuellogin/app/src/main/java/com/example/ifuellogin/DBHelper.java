package com.example.ifuellogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper{
    //create user db
    public static final String DBNAME = "users.db";
    public DBHelper(Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    //create user table
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create table users(email TEXT primary key, password TEXT, name TEXT, phone TEXT)");
    }

    @Override
    //check for ex
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email,String pass,String name,String phone ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email",email);
        contentValues.put("password",pass);
        contentValues.put("name",name);
        contentValues.put("phone",phone);

        long result = MyDB.insert("users",null,contentValues);

        if(result == -1) return false;
        else
            return true;

    }


    public Boolean checkemail (String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email=?",new String[] {email});
        if(cursor.getCount() >0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkemailpass (String email,String pass){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where email=? and password=?",new String[] {email,pass});
        if(cursor.getCount() >0){
            return true;
        }
        else {
            return false;
        }
    }
}
