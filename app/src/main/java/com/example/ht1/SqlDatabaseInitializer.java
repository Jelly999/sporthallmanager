package com.example.ht1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//SQL HELPER
public class SqlDatabaseInitializer extends SQLiteOpenHelper {

        // If you change the database schema, you must increment the database version.
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "sporthallmanager.db";

        public SqlDatabaseInitializer(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        public void onCreate(SQLiteDatabase db) {

            //Creation of the sql tables
            final String SQL_CREATE_USER =
                    "CREATE TABLE " + SqlTablenames.userTable.TABLE_NAME +
                            SqlTablenames.userTable.COLUMN_NAME_UUID +
                            SqlTablenames.userTable.COLUMN_NAME_USERNAME +
                            SqlTablenames.userTable.COLUMN_NAME_FIRSTNAME +
                            SqlTablenames.userTable.COLUMN_NAME_SURNAME +
                            SqlTablenames.userTable.COLUMN_NAME_SALT +
                            SqlTablenames.userTable.COLUMN_NAME_PWD_HASH +
                            SqlTablenames.userTable.COLUMN_NAME_ADMINISTRATOR;

            final String SQL_CREATE_SPORTHALL =
                    "CREATE TABLE " + SqlTablenames.sporthallTable.TABLE_NAME +
                            SqlTablenames.sporthallTable.COLUMN_NAME_HALLNAME +
                            SqlTablenames.sporthallTable.COLUMN_NAME_LOCATION +
                            SqlTablenames.sporthallTable.COLUMN_NAME_HALLTYPE +
                            SqlTablenames.sporthallTable.COLUMN_NAME_SPORT +
                            SqlTablenames.sporthallTable.COLUMN_NAME_NOT_AVAILABLE;

            final String SQL_CREATE_RESERVATION =
                    "CREATE TABLE " + SqlTablenames.reservationsTable.TABLE_NAME +
                            SqlTablenames.reservationsTable.COLUMN_NAME_RESERVEID +
                            SqlTablenames.reservationsTable.COLUMN_NAME_HALLNAME +
                            SqlTablenames.reservationsTable.COLUMN_NAME_DATE +
                            SqlTablenames.reservationsTable.COLUMN_NAME_START_TIME +
                            SqlTablenames.reservationsTable.COLUMN_NAME_DURATION +
                            SqlTablenames.reservationsTable.COLUMN_NAME_BOOKER +
                            SqlTablenames.reservationsTable.COLUMN_NAME_PARTICIPANTS +
                            SqlTablenames.reservationsTable.COLUMN_NAME_MAXPARTICIPANTS +
                            SqlTablenames.reservationsTable.COLUMN_NAME_RECURRING_EVENT;

            final String SQL_CREATE_ENROLLS =
                    "CREATE TABLE " + SqlTablenames.enrollsTable.TABLE_NAME +
                            SqlTablenames.enrollsTable.COLUMN_NAME_ENROLLID +
                            SqlTablenames.enrollsTable.COLUMN_NAME_HALLNAME +
                            SqlTablenames.enrollsTable.COLUMN_NAME_DATE +
                            SqlTablenames.enrollsTable.COLUMN_NAME_START_TIME +
                            SqlTablenames.enrollsTable.COLUMN_NAME_DURATION +
                            SqlTablenames.enrollsTable.COLUMN_NAME_USERNAME;

            final String SQL_CREATE_UNIVERSITIES =
                    "CREATE TABLE " + SqlTablenames.universitiesTable.TABLE_NAME +
                            SqlTablenames.universitiesTable.COLUMN_NAME_UUID +
                            SqlTablenames.universitiesTable.COLUMN_NAME_NAME +
                            SqlTablenames.universitiesTable.COLUMN_NAME_ADDRESS;

            final String SQL_CREATE_USER_ACCESS_UNI =
                    "CREATE TABLE " + SqlTablenames.user_access_uni_Table.TABLE_NAME +
                            SqlTablenames.user_access_uni_Table.COLUMN_NAME_ACCESS_UUID +
                            SqlTablenames.user_access_uni_Table.COLUMN_NAME_USER_UUID +
                            SqlTablenames.user_access_uni_Table.COLUMN_NAME_UNI_UUID;


            db.execSQL(SQL_CREATE_USER);
            db.execSQL(SQL_CREATE_SPORTHALL);
            db.execSQL(SQL_CREATE_RESERVATION);
            db.execSQL(SQL_CREATE_ENROLLS);
            db.execSQL(SQL_CREATE_UNIVERSITIES);
            db.execSQL(SQL_CREATE_USER_ACCESS_UNI);
        }


        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over

            //Delete functions for sql tables
            final String SQL_DELETE_USER =
                    "DROP TABLE IF EXISTS " + SqlTablenames.userTable.TABLE_NAME;

            final String SQL_DELETE_SPORTHALL =
                    "DROP TABLE IF EXISTS " + SqlTablenames.sporthallTable.TABLE_NAME;

            final String SQL_DELETE_RESERVATION =
                    "DROP TABLE IF EXISTS " + SqlTablenames.reservationsTable.TABLE_NAME;

            final String SQL_DELETE_ENROLLS =
                    "DROP TABLE IF EXISTS " + SqlTablenames.enrollsTable.TABLE_NAME;

            final String SQL_DELETE_UNIVERSITIES =
                    "DROP TABLE IF EXISTS " + SqlTablenames.universitiesTable.TABLE_NAME;

            final String SQL_DELETE_USER_ACCESS_UNI =
                    "DROP TABLE IF EXISTS " + SqlTablenames.user_access_uni_Table.TABLE_NAME;

            db.execSQL(SQL_DELETE_USER);
            db.execSQL(SQL_DELETE_SPORTHALL);
            db.execSQL(SQL_DELETE_RESERVATION);
            db.execSQL(SQL_DELETE_ENROLLS);
            db.execSQL(SQL_DELETE_UNIVERSITIES);
            db.execSQL(SQL_DELETE_USER_ACCESS_UNI);
            onCreate(db);
        }
}
