package com.assignment.moveinsyncassignment.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.assignment.moveinsyncassignment.database.DatabaseHelper;
import com.assignment.moveinsyncassignment.database.model.GenericStringModel;

import java.util.LinkedList;
import java.util.List;

public class DislikesDao extends BaseDao<GenericStringModel> {

    private static final String TABLE_DISLIKES = "tbl_dislikes";
    private static final String COL_URL = "url";
    public DatabaseHelper dbHelper;
    private Context context;

    public DislikesDao(Context context){
        super(context);
        this.context = context;
    }

    public static final String TABLE_DISLIKES_CREATE = "create table if not exists " + TABLE_DISLIKES + "("
            + COL_URL + " text not null);";

    public boolean insertPhotoLocal(String likedPhoto) {
        DatabaseHelper dbHelper2 = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper2.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_URL, likedPhoto);
        long result = db.insert(TABLE_DISLIKES,null,values);

        if(result == -1)
            return false;
        else
            return true;
    }

    public void deleteData (String photo) {
        dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_DISLIKES + " WHERE url = '" + photo + "';";
        db.execSQL(query);
    }

    public List<String> getDislikesList() {
        List<String> dislikesList;
        dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_DISLIKES,null);
        res.moveToFirst();
        dislikesList = convertCursorToBreed(res);
        return dislikesList;
    }

    private List<String> convertCursorToBreed(Cursor uCur) {
        List<String> dislikesList = new LinkedList<>();
        if (uCur.getCount() > 0) {
            do {
                dislikesList.add(uCur.getString(uCur.getColumnIndex(COL_URL)));
            } while (uCur.moveToNext());
        }
        uCur.close();
        return dislikesList;
    }

}
