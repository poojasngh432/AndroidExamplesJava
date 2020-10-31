package com.assignment.moveinsyncassignment.database.dao;

import android.content.Context;

import com.assignment.moveinsyncassignment.database.RMSMngr;

public abstract class BaseDao<T> {

    static public RMSMngr objDatabase;
    protected Context mContext;

    static {
        objDatabase = new RMSMngr();
    }

    public BaseDao(final Context context){
        mContext = context;
    }

    protected BaseDao() {
        objDatabase = new RMSMngr();
    }

}
