package com.example.tutorialsproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "main.db";
    private static final String TAG = DatabaseHelper.class.getName();
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        RMSManager.createTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
       switch (oldV){
           case 1:  execSQL("alter table tbl_stores add column store_address Text", sqLiteDatabase);
                    break;
           default: break;
       }
    }

    private void execSQL(String sql, SQLiteDatabase db) {
        try {
            db.execSQL(sql);
        } catch (SQLiteException ex) {
            ex.printStackTrace();
        }
    }
}
