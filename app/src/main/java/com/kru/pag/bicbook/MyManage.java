package com.kru.pag.bicbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pag on 30/3/2559.
 */
public class MyManage {

    //Explicit
    private MyOpneHelper myOpneHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String column_id = "_id";
    public static final String column_User = "User";
    public static final String column_Password = "Password";
    public static final String column_Name = "Name";
    public static final String column_IDcard = "IDcard";


    public static final String service_table = "serviceTABLE";

    public static final String column_IDcar = "IDcar";
    public static final String column_Image = "Image";
    public static final String column_Description = "Description";
    public static final String column_Price = "Price";

    public MyManage(Context context) {

        myOpneHelper = new MyOpneHelper(context);
        sqLiteDatabase = myOpneHelper.getWritableDatabase();

    }//Constructor


    public long addService(String strIDcar,
                           String strImage,
                           String strDescription,
                           String strPrice) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_IDcar, strIDcar);
        contentValues.put(column_Image, strImage);
        contentValues.put(column_Description, strDescription);
        contentValues.put(column_Price, strPrice);
        return sqLiteDatabase.insert(service_table, null, contentValues);

    }

    public long addUser(String strUser,
                        String strPassword,
                        String strName,
                        String strIDcard) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_User, strUser);
        contentValues.put(column_Password, strPassword);
        contentValues.put(column_Name, strName);
        contentValues.put(column_IDcard, strIDcard);

        return sqLiteDatabase.insert(user_table, null, contentValues);
    }

}// Main Class
