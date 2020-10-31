package com.assignment.moveinsyncassignment.database;

import android.database.sqlite.SQLiteDatabase;

import com.assignment.moveinsyncassignment.database.dao.AllProductsDao;
import com.assignment.moveinsyncassignment.database.dao.DislikesDao;
import com.assignment.moveinsyncassignment.database.dao.LikesDao;

public class RMSMngr {
    private static final String TAG = RMSMngr.class.getSimpleName();

    public RMSMngr(){

    }

    public static void createTables(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(AllProductsDao.TABLE_ALL_BREEDS_CREATE);
            sqLiteDatabase.execSQL(LikesDao.TABLE_LIKES_CREATE);
            sqLiteDatabase.execSQL(DislikesDao.TABLE_DISLIKES_CREATE);
        }catch (Exception ex) {
        ex.printStackTrace();
        }
    }

}
