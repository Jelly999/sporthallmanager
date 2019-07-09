package com.example.ht1;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class SqlManager {



    SqlDatabaseInitializer dbHelper = new SqlDatabaseInitializer(context);//TODO getContext toimimaan ?

    // Lets set some values to the database
    SQLiteDatabase db = dbHelper.getWritableDatabase();


    // TODO alustukset taulukoille ennalta määritetyillä arvoilla
    ContentValues values = new ContentValues();
    values.put(FeedEntry.COLUMN_NAME_TITLE, title);
    values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);

    // Insert the new row, returning the primary key value of the new row
    long newRowId = db.insert(FeedEntry.TABLE_NAME, null, values);
}
