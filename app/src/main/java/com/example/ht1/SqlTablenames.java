package com.example.ht1;

import android.provider.BaseColumns;


/*LINKS TO SQL DATABASE GUIDES
https://developer.android.com/training/data-storage/sqlite
https://www.tutorialspoint.com/android/android_sqlite_database.htm
https://www.tutorialspoint.com/sqlite/sqlite_java.htm
 */


public final class SqlTablenames {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private SqlTablenames() {}

    /* Inner class that defines the table contents */
    public static class userTable implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USER_UUID = "uuid";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_FIRSTNAME = "firstname";
        public static final String COLUMN_NAME_SURNAME = "surname";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONE_NUMBER = "phone number";
        public static final String COLUMN_NAME_PWD_HASH = "pwd hash";
        public static final String COLUMN_NAME_ADMINISTRATOR = "administrator";
    }

    public static class sporthallTable implements BaseColumns {
        public static final String TABLE_NAME = "sporthall";
        public static final String COLUMN_NAME_HALLID = "uuid";
        public static final String COLUMN_NAME_HALLNAME = "hallname";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_HALLTYPE = "halltype";
        public static final String COLUMN_NAME_SPORT = "sport";
        public static final String COLUMN_NAME_NOT_AVAILABLE = "not available";
    }

    public static class reservationsTable implements BaseColumns {
        public static final String TABLE_NAME = "reservations";
        public static final String COLUMN_NAME_RESERVEID = "reserveid";
        public static final String COLUMN_NAME_HALLID = "uuid";
        public static final String COLUMN_NAME_START_TIME = "start time";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_USER_UUID = "user uuid";
        public static final String COLUMN_NAME_MAXPARTICIPANTS = "maxparticipants";
        public static final String COLUMN_NAME_RECURRING_EVENT = "recurring event";
    }

    public static class enrollsTable implements BaseColumns {
        public static final String TABLE_NAME = "enrolls";
        public static final String COLUMN_NAME_ENROLLID = "enrollid";
        public static final String COLUMN_NAME_USER_UUID = "uuid";
        public static final String COLUMN_NAME_RESERVEID = "reserveid";
    }

    public static class universitiesTable implements BaseColumns {
        public static final String TABLE_NAME = "universities";
        public static final String COLUMN_NAME_UNI_UUID = "uuid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
    }

    public static class user_access_uni_Table implements BaseColumns {
        public static final String TABLE_NAME = "user_access_uni";
        public static final String COLUMN_NAME_ACCESS_UUID = "access uuid";
        public static final String COLUMN_NAME_USER_UUID = "user uuid";
        public static final String COLUMN_NAME_UNI_UUID = "uni uuid";
    }

}
