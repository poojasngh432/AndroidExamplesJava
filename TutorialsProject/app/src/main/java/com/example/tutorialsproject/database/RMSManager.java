package com.example.tutorialsproject.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.databinding.BaseObservable;

import com.example.tutorialsproject.BaseClass;

import java.util.ArrayList;

public class RMSManager {
    private static final String TAG = RMSManager.class.getSimpleName();
    private SQLiteDatabase sqLiteDatabase;

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     */
    // First Call from Main Activity Class
    public RMSManager() {

    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     * initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    // Second Call from Main Activity Class
//    public void open() throws SQLException
//    {
//        getWritableDB()Helper = Gac.getInstance().getDbHelper();
//        getWritableDB() = getWritableDB()Helper.getWritableDatabase();
//        CreateTables();
//    }
    public static void createTables(SQLiteDatabase sqLiteDatabase) {
        try {
            //sqLiteDatabase.execSQL(TblAbcDao.TABLE_ABC_CREATE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public long WriteSingleRecord(ContentValues initialValues, String tableName) {
        try {
            return getWritableDB().insertOrThrow(tableName, null, initialValues);
        } catch (SQLException e) {
            Log.e(TAG, "Error inserting " + initialValues, e);
            return -1;
        }
    }

    // Created to throw exception in case of error so we can rollback the complete transaction in case of master data inbound. Currently used in market activity inbound.
    public long WriteOrThrowSingleRecord(ContentValues initialValues, String tableName) throws SQLException {
        return getWritableDB().insertOrThrow(tableName, null, initialValues);
    }

    public boolean DeleteSingleRecord(String colName, long keyVal, String tableName) {
        int ret = getWritableDB().delete(tableName, colName + "=" + keyVal, null);
        return ret > 0;
    }

    public boolean DeleteAllRecord(String tableName) {
        int ret = getWritableDB().delete(tableName, null, null);
        return ret > 0;
    }

    public Cursor ReadSingleRecord(String colName, int keyVal, String tableName) throws SQLException {
        Cursor mCursor =
                getWritableDB().query(true, tableName, null, colName + "=" + keyVal, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean UpdateSingleRecord(long keyVal, String colName, ContentValues args, String tableName) {
        int ret;
        ret = getWritableDB().update(tableName, args, colName + "=" + keyVal, null);

        return ret > 0;
    }

    public Cursor execRawQuery(String query) {
        Cursor c = getWritableDB().rawQuery(query, null);
        if (c != null)
            c.moveToFirst();
        return c;
    }

    private ArrayList<ArrayList<String>> getDataListFromTable(String tablename) {

        ArrayList<ArrayList<String>> retList = new ArrayList<>();
        ArrayList<String> list;
        Cursor cursor = execRawQuery("select * from " + tablename);
        if (cursor.moveToFirst()) {
            do {
                list = new ArrayList<>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    list.add(cursor.getString(i));
                }
                retList.add(list);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return retList;

    }

    private ArrayList<ArrayList<String>> getDataListFromQuery(String query) {

        ArrayList<ArrayList<String>> retList = new ArrayList<>();
        ArrayList<String> list;
        Cursor cursor = execRawQuery(query);
        if (cursor.moveToFirst()) {
            do {
                list = new ArrayList<>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    list.add(cursor.getString(i));
                }
                retList.add(list);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return retList;

    }

    public void executeUpdate(String query) {
        getWritableDB().execSQL(query);
    }

    public SQLiteDatabase getWritableDB() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = BaseClass.getInstance().getDbHelper().getWritableDatabase();
        }
        if (!sqLiteDatabase.isOpen()) {
            sqLiteDatabase = BaseClass.getInstance().getDbHelper().getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public void execRawSql(String query) {
        sqLiteDatabase.execSQL(query);
    }

    public void UpdateSingleRecord(String keyVal, String colName, ContentValues args, String tableName) {
        int ret = 0;
        getWritableDB().beginTransaction();
        try {
            ret = getWritableDB().update(tableName, args, colName + "= '" + keyVal + "'", null);
            getWritableDB().setTransactionSuccessful();
        } finally {
            getWritableDB().endTransaction();
            getWritableDB().close();
        }
    }

    public void closeDb() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

}