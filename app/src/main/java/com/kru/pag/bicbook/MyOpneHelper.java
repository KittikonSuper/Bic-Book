package com.kru.pag.bicbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pag on 30/3/2559.
 */
public class MyOpneHelper extends SQLiteOpenHelper{
    //Explicit
    public static final String databese_name = "bangData.db";
    private static final int database_version = 1;

    private static final String create_user_table = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text" +
            "Password text," +
            "Name text," +
            "IDcard text);";



    private static final String create_service_table = "create table serviceTABLE (" +
            "_id integer primary key," +
            "IDcar text," +
            "Image text," +
            "Description text," +
            "Price text);";

    public MyOpneHelper(Context context) {
        super(context, databese_name, null, database_version);

    } //Constructer

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);
        sqLiteDatabase.execSQL(create_service_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}// Main Class
