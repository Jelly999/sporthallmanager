package com.example.ht1;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class SqlManager {


    private static SqlManager uniqueInstance;

    SqlManager(Context context) {
        SqlDatabaseInitializer dbHelper = new SqlDatabaseInitializer(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    public static SqlManager getInstance(Context context) {
        if (uniqueInstance == null) {
            uniqueInstance = new SqlManager(context);
        }
        return uniqueInstance;
    }

    //TODO tänne ennalta määritetyt taulukon sisällöt käyttämällä SQLwriteRowia



    //Methods for writing, deletin and updating sql tables
    public void SQLwriteRow() {

    }

    public void SQLdeleteRow() {

    }

    public void SQLupdateRow() {

    }
}
