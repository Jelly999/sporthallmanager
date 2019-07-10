package com.example.ht1;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class SqlManager {


    private static SqlManager uniqueInstance;

    private static SQLiteDatabase Wdb; //TODO Tämä varmaan pitänee olla täällä?

    SqlManager(Context context) {
        SqlDatabaseInitializer dbHelper = new SqlDatabaseInitializer(context);
        Wdb = dbHelper.getWritableDatabase();
        Rdb = dbHelper.getReadableDatabase();

    }

    public static SqlManager getInstance(Context context) {
        if (uniqueInstance == null) {
            uniqueInstance = new SqlManager(context);
        }
        return uniqueInstance;
    }

    //TODO tänne ennalta määritetyt taulukon sisällöt käyttämällä SQLwriteRowia
    //TODO Toimiiko tämä



    public static class SQLuser {

        private static String TABLE_NAME;
        private static String USER_UUID;

        SQLuser() {
            TABLE_NAME = SqlTablenames.userTable.TABLE_NAME;
            USER_UUID = SqlTablenames.userTable.COLUMN_NAME_USER_UUID;
        }

        public static void insertRow(String[] userInfo) {
            String SQLquery = "INSERT INTO " + TABLE_NAME + "(" +
                    SqlTablenames.userTable.COLUMN_NAME_USERNAME + "," +
                    SqlTablenames.userTable.COLUMN_NAME_FIRSTNAME + "," +
                    SqlTablenames.userTable.COLUMN_NAME_SURNAME + "," +
                    SqlTablenames.userTable.COLUMN_NAME_EMAIL + "," +
                    SqlTablenames.userTable.COLUMN_NAME_PHONE_NUMBER + "," +
                    SqlTablenames.userTable.COLUMN_NAME_PWD_HASH + "," +
                    SqlTablenames.userTable.COLUMN_NAME_ADMINISTRATOR +
                    ") VALUES " + "(";
            for (int i = 0; i < userInfo.length; i++) {
                SQLquery += userInfo[i];
                if (i < (userInfo.length-1)) {
                    SQLquery += ",";
                }
            }
            SQLquery += ");";

            Wdb.execSQL(SQLquery);
        }

        public void updateRow(String UUID, String COLUMN_NAME, String DATA) {
            String SQLquery = "UPDATE " + TABLE_NAME +
                    " SET " + COLUMN_NAME + " = " + DATA +
                    " WHERE " + USER_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }

        public void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + TABLE_NAME +
                    " WHERE " + USER_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }

    public static class SQLsporthall{

        private static String TABLE_NAME;
        private static String HALL_UUID;

        SQLsporthall() {
            TABLE_NAME = SqlTablenames.sporthallTable.TABLE_NAME;
            HALL_UUID = SqlTablenames.sporthallTable.COLUMN_NAME_HALLID;
        }

        public static void insertRow(String[] hallInfo) {
            String SQLquery = "INSERT INTO " + TABLE_NAME + "(" +
                    SqlTablenames.sporthallTable.COLUMN_NAME_HALLNAME + "," +
                    SqlTablenames.sporthallTable.COLUMN_NAME_LOCATION + "," +
                    SqlTablenames.sporthallTable.COLUMN_NAME_HALLTYPE + "," +
                    SqlTablenames.sporthallTable.COLUMN_NAME_SPORT + "," +
                    SqlTablenames.sporthallTable.COLUMN_NAME_NOT_AVAILABLE +
                    ") VALUES " + "(";
            for (int i = 0; i < hallInfo.length; i++) {
                SQLquery += hallInfo[i];
                if (i < (hallInfo.length-1)) {
                    SQLquery += ",";
                }
            }
            SQLquery += ");";

            Wdb.execSQL(SQLquery);
        }

        public void updateRow(String UUID, String COLUMN_NAME, String DATA) {
            String SQLquery = "UPDATE " + TABLE_NAME +
                    " SET " + COLUMN_NAME + " = " + DATA +
                    " WHERE " + HALL_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }

        public void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + TABLE_NAME +
                    " WHERE " + HALL_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }

    }

    public static class SQLreservation {

        private static String TABLE_NAME;
        private static String RESERVE_UUID;

        SQLreservation() {
            TABLE_NAME = SqlTablenames.reservationsTable.TABLE_NAME;
            RESERVE_UUID = SqlTablenames.reservationsTable.COLUMN_NAME_RESERVEID;
        }

        public static void insertRow(String[] userInfo) {
            String SQLquery = "INSERT INTO " + TABLE_NAME + "(" +
                    SqlTablenames.reservationsTable.COLUMN_NAME_RESERVEID + "," +
                    SqlTablenames.reservationsTable.COLUMN_NAME_HALLID + "," +
                    SqlTablenames.reservationsTable.COLUMN_NAME_START_TIME + "," +
                    SqlTablenames.reservationsTable.COLUMN_NAME_DURATION + "," +
                    SqlTablenames.reservationsTable.COLUMN_NAME_USER_UUID + "," +
                    SqlTablenames.reservationsTable.COLUMN_NAME_MAXPARTICIPANTS + "," +
                    SqlTablenames.reservationsTable.COLUMN_NAME_RECURRING_EVENT +
                    ") VALUES " + "(";
            for (int i = 0; i < userInfo.length; i++) {
                SQLquery += userInfo[i];
                if (i < (userInfo.length-1)) {
                    SQLquery += ",";
                }
            }
            SQLquery += ");";

            Wdb.execSQL(SQLquery);
        }

        public void updateRow(String UUID, String COLUMN_NAME, String DATA) {
            String SQLquery = "UPDATE " + TABLE_NAME +
                    " SET " + COLUMN_NAME + " = " + DATA +
                    " WHERE " + RESERVE_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }

        public void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + TABLE_NAME +
                    " WHERE " + RESERVE_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }
}
