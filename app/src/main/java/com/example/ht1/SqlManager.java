package com.example.ht1;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.ht1.SqlTablenames;


public class SqlManager {


    private static SqlManager uniqueInstance;

    static SQLiteDatabase db; //TODO Tämä varmaan pitänee olla täällä?

    SqlManager(Context context) {
        SqlDatabaseInitializer dbHelper = new SqlDatabaseInitializer(context);
        db = dbHelper.getWritableDatabase();
    }

    public static SqlManager getInstance(Context context) {
        if (uniqueInstance == null) {
            uniqueInstance = new SqlManager(context);
        }
        return uniqueInstance;
    }

    //TODO tänne ennalta määritetyt taulukon sisällöt käyttämällä SQLwriteRowia

    //SqlManager.SQLuser.;


    public static class SQLuser {

        private String tableName;

        SQLuser() {
            tableName = SqlTablenames.userTable.TABLE_NAME;
        }

        public void insertRow(String[] userInfo) {
            String SQLquery = "INSERT INTO " + tableName + "(" +
                    SqlTablenames.userTable.COLUMN_NAME_USERNAME + "," +
                    SqlTablenames.userTable.COLUMN_NAME_FIRSTNAME + "," +
                    SqlTablenames.userTable.COLUMN_NAME_SURNAME + "," +
                    SqlTablenames.userTable.COLUMN_NAME_EMAIL + "," +
                    SqlTablenames.userTable.COLUMN_NAME_PHONE_NUMBER + "," +
                    SqlTablenames.userTable.COLUMN_NAME_PWD_HASH + "," +
                    SqlTablenames.userTable.COLUMN_NAME_ADMINISTRATOR + "," +
                    ") VALUES " + "(";
            for (int i = 0; i < userInfo.length; i++) {
                SQLquery += userInfo[i];
                if (i < (userInfo.length-1)) {
                    SQLquery += ",";
                }
            }
            SQLquery += ");";

            db.execSQL(SQLquery);
        }

        public void updateRow() {
            //
        }

        public void removeRow() {
            //
        }
    }

    public static class SQLsporthall extends SQLuser{

        SQLsporthall() {

        }

    }

    public static class SQLreservation extends SQLuser {

        SQLreservation() {

        }
    }
}
