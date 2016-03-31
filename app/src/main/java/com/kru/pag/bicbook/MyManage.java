package com.kru.pag.bicbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pag on 30/3/2559.
 */
public class MyManage {

    //Explicit
    private MyOpneHelper myOpneHelper;
    private SQLiteDatabase sqLiteDatabase;
    public MyManage (Context context) {

        myOpneHelper = new MyOpneHelper(context);
        sqLiteDatabase = myOpneHelper.getWritableDatabase();

    }//Constructor

}// Main Class
