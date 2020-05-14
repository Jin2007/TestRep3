package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "surname text,"
                + "sex text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
