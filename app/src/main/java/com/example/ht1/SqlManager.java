package com.example.ht1;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class SqlManager {


    private static SqlManager uniqueInstance;

    private static SQLiteDatabase Wdb; //TODO Tämä varmaan pitänee olla täällä?
    private static SQLiteDatabase Rdb; //TODO Tätä varmaan TARVITAAN/KÄYTETÄÄN?

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
                    SqlTablenames.sporthallTable.COLUMN_NAME_UNI_UUID + "," +
                    SqlTablenames.sporthallTable.COLUMN_NAME_HALLTYPE + "," +
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
                    SqlTablenames.reservationsTable.COLUMN_NAME_HALLID + "," +
                    SqlTablenames.reservationsTable.COLUMN_NAME_SPORT + "," +
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

    //Methods for adding and removing enrolls
    public static class SQLenrolls {

        private static String TABLE_NAME;
        private static String ENROLLID;

        SQLenrolls() {
            TABLE_NAME = SqlTablenames.enrollsTable.TABLE_NAME;
            ENROLLID = SqlTablenames.enrollsTable.COLUMN_NAME_ENROLLID;
        }

        public static void insertRow(String USER_UUID, String RESERVEID) {
            String SQLquery = "INSERT INTO " + TABLE_NAME + " (" +
                    SqlTablenames.enrollsTable.COLUMN_NAME_USER_UUID + "," +
                    SqlTablenames.enrollsTable.COLUMN_NAME_RESERVEID +
                    ") VALUES " + "(" + USER_UUID + ", " + RESERVEID + ");";

            Wdb.execSQL(SQLquery);
        }

        public void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + TABLE_NAME +
                    " WHERE " + ENROLLID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }

    //Methods for adding, updating and removing universities
    public static class SQLuniversities {

        private static String TABLE_NAME;
        private static String UNI_UUID;

        SQLuniversities() {
            TABLE_NAME = SqlTablenames.universitiesTable.TABLE_NAME;
            UNI_UUID = SqlTablenames.universitiesTable.COLUMN_NAME_UNI_UUID;
        }

        public static void insertRow(String UNI_NAME, String UNI_ADDRESS) {
            String SQLquery = "INSERT INTO " + TABLE_NAME + " (" +
                    SqlTablenames.universitiesTable.COLUMN_NAME_NAME + "," +
                    SqlTablenames.universitiesTable.COLUMN_NAME_ADDRESS +
                    ") VALUES " + "(" + UNI_NAME + ", " + UNI_ADDRESS + ");";

            Wdb.execSQL(SQLquery);
        }

        public void updateRow(String UUID, String COLUMN_NAME, String DATA) {
            String SQLquery = "UPDATE " + TABLE_NAME +
                    " SET " + COLUMN_NAME + " = " + DATA +
                    " WHERE " + UNI_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }

        public void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + TABLE_NAME +
                    " WHERE " + UNI_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }

    //Methods for adding and removing user access tu universities
    public static class SQLaccess {

        private static String TABLE_NAME;
        private static String ACCESS_UUID;

        SQLaccess() {
            TABLE_NAME = SqlTablenames.user_access_uni_Table.TABLE_NAME;
            ACCESS_UUID = SqlTablenames.user_access_uni_Table.COLUMN_NAME_ACCESS_UUID;
        }

        public static void insertRow(String USER_UUID, String UNI_UUID) {
            String SQLquery = "INSERT INTO " + TABLE_NAME + " (" +
                    SqlTablenames.user_access_uni_Table.COLUMN_NAME_USER_UUID + "," +
                    SqlTablenames.user_access_uni_Table.COLUMN_NAME_UNI_UUID +
                    ") VALUES " + "(" + USER_UUID + ", " + UNI_UUID + ");";

            Wdb.execSQL(SQLquery);
        }

        public void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + TABLE_NAME +
                    " WHERE " + ACCESS_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }


    public static User[] getUserFromDatabase() throws SQLException {
        String query = "SELECT * FROM ";

        return null;
    }

    /////////////////////////////////
    //PRESETTING VALUES TO DATABASE//
    /////////////////////////////////

    public void presetDatabaseValues() {

        //User preset values
        String[] user = {"admin", "admin", "admin", "admin.admin@adminmail.com", "0500628689", PasswordManager.getHashedPassword("admin", "admin"), "1" };
        SQLuser.insertRow(user);
        user = new String[]{"mattim", "Matti", "Meikäläinen", "Matti.Meikalainen@gmail.com", "0505689132", PasswordManager.getHashedPassword("44mUj40nP4lJ0n", "mattim"), "0"};
        SQLuser.insertRow(user);
        user = new String[]{"rickv", "Rick", "Vang", "Rick.Vang@webmail.com", "0290909857", PasswordManager.getHashedPassword("AuR1nk#1n€n12?", "rickv"), "0"};
        SQLuser.insertRow(user);
        user = new String[]{"jimb", "Jim", "Bass", "jimba89@gmail.com", "0440698602", PasswordManager.getHashedPassword("H3LL0@w0rld!!!", "jimb"), "0"};
        SQLuser.insertRow(user);
        user = new String[]{"jonh", "John", "Denton", "Jonny.Boii@memes.com", "0400568223", PasswordManager.getHashedPassword("M€€mut0nK1v0ja", "jonh"), "0"};
        SQLuser.insertRow(user);
        user = new String[]{"gregn", "Greg", "Novak", "Gregori.Nova@slavmail.ru", "0440666869", PasswordManager.getHashedPassword("##N0tY0uR5But0ur5##", "gregn"), "0"};
        SQLuser.insertRow(user);
        user = new String[]{"omarm", "Omar", "Marshall", "FieldMarshall@USAmail.com", "0500911420", PasswordManager.getHashedPassword("M4k€US4Gr€4T4G4in", "omarm"), "0"};
        SQLuser.insertRow(user);
        user = new String[]{"miac", "Mia", "Croft", "mia.croft@onlinemail.com", "0670884925", PasswordManager.getHashedPassword("M€€mut0nK1v0ja", "miac"), "0"};
        SQLuser.insertRow(user);

        //Universities preset values
        SQLuniversities.insertRow("LUT", "Yliopistonkatu 34, 53850 Lappeenranta");

        //User_access_uni preset values
        SQLaccess.insertRow("1", "1");
        SQLaccess.insertRow("2", "1");
        SQLaccess.insertRow("3", "1");
        SQLaccess.insertRow("4", "1");
        SQLaccess.insertRow("5", "1");
        SQLaccess.insertRow("6", "1");
        SQLaccess.insertRow("7", "1");
        SQLaccess.insertRow("8", "1");

        //Sporthall preset values
        String[] hall = { "Gerpiili", "1", "Multipurpose", "0" };
        SQLsporthall.insertRow(hall);
        hall = new String[]{ "Kerpiili", "1", "Badminton", "0" };
        SQLsporthall.insertRow(hall);
        hall = new String[]{ "Kerbiili", "1", "Basketball", "0" };
        SQLsporthall.insertRow(hall);
        hall = new String[]{ "Gerbiili", "1", "Gym", "0" };

        //Reservations preset values
        String[] reserved = { "2", "Floorball", "2019-07-20T14:00", "2", "2", "20", "0" };
        SQLreservation.insertRow(reserved);
        reserved = new String[]{ "2", "Badminton", "2019-07-20T16:00", "4", "3", "10", "0" };
        SQLreservation.insertRow(reserved);

        /* NÄMÄ TULEE RESERVATIOIHIN //TODO kellon ajat oikein databaseen
        INSERT INTO "reservations"
        VALUES (2265812, 'Kerpiili', '20.07.2019', 1400, 2, 'mattim', 1, 20,0);
        INSERT INTO "reservations"
        VALUES (2265813, 'Kerpiili', '20.07.2019', 1600, 4, 'rickv', 4, 10,0);

        COLUMN_NAME_HALLID
        COLUMN_NAME_SPORT
        COLUMN_NAME_START_TIME
        COLUMN_NAME_DURATION
        COLUMN_NAME_USER_UUID
        COLUMN_NAME_MAXPARTICIPANTS
        COLUMN_NAME_RECURRING_EVENT*/

        //Enrolls preset values
        SQLenrolls.insertRow("3", "1");
        SQLenrolls.insertRow("4", "1");
        SQLenrolls.insertRow("5", "1");
        SQLenrolls.insertRow("6", "1");
        SQLenrolls.insertRow("2", "2");
        SQLenrolls.insertRow("7", "2");
        SQLenrolls.insertRow("8", "2");
        SQLenrolls.insertRow("1", "2");

    }
}