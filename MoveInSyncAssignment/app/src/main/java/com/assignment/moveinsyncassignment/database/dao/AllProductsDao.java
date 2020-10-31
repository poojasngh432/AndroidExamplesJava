package com.assignment.moveinsyncassignment.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.assignment.moveinsyncassignment.database.DatabaseHelper;
import com.assignment.moveinsyncassignment.database.model.Product;
import java.util.LinkedList;
import java.util.List;

public class AllProductsDao extends BaseDao<Product>{

    private static final String TABLE_PRODUCTS = "all_products";
    private static final String COL_NAME = "name";
    private static final String COL_DESC = "desc";
    private static final String COL_PRICE = "price";
    private static final String COL_URL = "url";
    public DatabaseHelper dbHelper;
    private Context context;

    public AllProductsDao(Context context){
        super(context);
        this.context = context;
    }

    public static final String TABLE_ALL_BREEDS_CREATE = "create table if not exists " + TABLE_PRODUCTS + "("
            + COL_NAME + " text not null,"
            + COL_DESC + " text not null,"
            + COL_PRICE + " text not null,"
            + COL_URL + " text not null)";

    public void insertProductsLocal(List<Product> data) {
        dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        for(Product s: data){
            values.put(COL_NAME, s.getName());
            values.put(COL_DESC, s.getDescription());
            values.put(COL_PRICE, s.getPrice());
            values.put(COL_URL, s.getUrlString());

            db.insert(TABLE_PRODUCTS,null,values);
        }
    }

    public List<Product> getProductsList() {
        List<Product> productsList;
        dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_PRODUCTS,null);
        res.moveToFirst();
        productsList = convertCursorToBreed(res);
        return productsList;
    }

    private List<Product> convertCursorToBreed(Cursor uCur) {
        List<Product> productsList = new LinkedList<>();
        if (uCur.getCount() > 0) {
            do {
                Product p = new Product();
                p.setName(uCur.getString(uCur.getColumnIndex(COL_NAME)));
                p.setDescription(uCur.getString(uCur.getColumnIndex(COL_DESC)));
                p.setPrice(Integer.parseInt(uCur.getString(uCur.getColumnIndex(COL_PRICE))));
                p.setUrlString(uCur.getString(uCur.getColumnIndex(COL_URL)));

                productsList.add(p);
            } while (uCur.moveToNext());
        }
        uCur.close();
        return productsList;
    }

}

