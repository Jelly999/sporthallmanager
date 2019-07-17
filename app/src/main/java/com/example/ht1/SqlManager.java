package com.example.ht1;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SqlManager {


    private static SqlManager uniqueInstance;

    private static SQLiteDatabase Wdb; //TODO Tämä varmaan pitänee olla täällä?
    private static SQLiteDatabase Rdb; //TODO Tätä varmaan TARVITAAN/KÄYTETÄÄN?

    SqlManager(Context context) {
        SqlDatabaseInitializer dbHelper = new SqlDatabaseInitializer(context);
        Wdb = dbHelper.getWritableDatabase();
        Rdb = dbHelper.getReadableDatabase();
        // Lets ser PRAGMA foreign_keys = ON; so cascade will work
        String SQLquery = "PRAGMA foreign_keys = ON;";
        Wdb.execSQL(SQLquery);
    }


    public static SqlManager getInstance(Context context) {
        if (uniqueInstance == null) {
            uniqueInstance = new SqlManager(context);
        }
        return uniqueInstance;
    }


    public static class SQLuser {

        public static void insertRow(String[] userInfo) {
            String SQLquery = "INSERT INTO " + SqlTablenames.userTable.TABLE_NAME + "(" +
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


        public static void updateRow(String UUID, String COLUMN_NAME, String DATA) {
            String SQLquery = "UPDATE " + SqlTablenames.userTable.TABLE_NAME +
                    " SET " + COLUMN_NAME + " = " + DATA +
                    " WHERE " + SqlTablenames.userTable.COLUMN_NAME_USER_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }

        public static void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + SqlTablenames.userTable.TABLE_NAME +
                    " WHERE " + SqlTablenames.userTable.COLUMN_NAME_USER_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }


    public static class SQLsporthall{

        public static void insertRow(String[] hallInfo) {
            String SQLquery = "INSERT INTO " + SqlTablenames.sporthallTable.TABLE_NAME + "(" +
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


        public static void updateRow(String UUID, String COLUMN_NAME, String DATA) {
            String SQLquery = "UPDATE " + SqlTablenames.sporthallTable.TABLE_NAME +
                    " SET " + COLUMN_NAME + " = " + DATA +
                    " WHERE " + SqlTablenames.sporthallTable.COLUMN_NAME_HALLID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }


        public static void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + SqlTablenames.sporthallTable.TABLE_NAME +
                    " WHERE " + SqlTablenames.sporthallTable.COLUMN_NAME_HALLID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }


    public static class SQLreservation {

        public static void insertRow(String[] userInfo) {
            String SQLquery = "INSERT INTO " + SqlTablenames.reservationsTable.TABLE_NAME + "(" +
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


        public static void updateRow(String UUID, String COLUMN_NAME, String DATA) {
            String SQLquery = "UPDATE " + SqlTablenames.reservationsTable.TABLE_NAME +
                    " SET " + COLUMN_NAME + " = " + DATA +
                    " WHERE " + SqlTablenames.reservationsTable.COLUMN_NAME_RESERVEID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }


        public static void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + SqlTablenames.reservationsTable.TABLE_NAME +
                    " WHERE " + SqlTablenames.reservationsTable.COLUMN_NAME_RESERVEID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }

    //Methods for adding and removing enrolls
    public static class SQLenrolls {

        public static void insertRow(String USER_UUID, String RESERVEID) {
            String SQLquery = "INSERT INTO " + SqlTablenames.enrollsTable.TABLE_NAME + " (" +
                    SqlTablenames.enrollsTable.COLUMN_NAME_USER_UUID + "," +
                    SqlTablenames.enrollsTable.COLUMN_NAME_RESERVEID +
                    ") VALUES " + "(" + USER_UUID + ", " + RESERVEID + ");";

            Wdb.execSQL(SQLquery);
        }


        public static void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + SqlTablenames.enrollsTable.TABLE_NAME +
                    " WHERE " + SqlTablenames.enrollsTable.COLUMN_NAME_ENROLLID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }

    //Methods for adding, updating and removing universities
    public static class SQLuniversities {

        public static void insertRow(String UNI_NAME, String UNI_ADDRESS) {
            String SQLquery = "INSERT INTO " + SqlTablenames.universitiesTable.TABLE_NAME + " (" +
                    SqlTablenames.universitiesTable.COLUMN_NAME_NAME + "," +
                    SqlTablenames.universitiesTable.COLUMN_NAME_ADDRESS +
                    ") VALUES " + "(" + UNI_NAME + ", " + UNI_ADDRESS + ");";

            Wdb.execSQL(SQLquery);
        }


        public static void updateRow(String UUID, String COLUMN_NAME, String DATA) {
            String SQLquery = "UPDATE " + SqlTablenames.universitiesTable.TABLE_NAME +
                    " SET " + COLUMN_NAME + " = " + DATA +
                    " WHERE " + SqlTablenames.universitiesTable.COLUMN_NAME_UNI_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }


        public static void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + SqlTablenames.universitiesTable.TABLE_NAME +
                    " WHERE " + SqlTablenames.universitiesTable.COLUMN_NAME_UNI_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }

    //Methods for adding and removing user access to universities
    public static class SQLaccess {

        public static void insertRow(String USER_UUID, String UNI_UUID) {
            String SQLquery = "INSERT INTO " + SqlTablenames.user_access_uni_Table.TABLE_NAME + " (" +
                    SqlTablenames.user_access_uni_Table.COLUMN_NAME_USER_UUID + "," +
                    SqlTablenames.user_access_uni_Table.COLUMN_NAME_UNI_UUID +
                    ") VALUES " + "(" + USER_UUID + ", " + UNI_UUID + ");";

            Wdb.execSQL(SQLquery);
        }


        public static void removeRow(String UUID) {
            String SQLquery = "DELETE FROM " + SqlTablenames.user_access_uni_Table.TABLE_NAME +
                    " WHERE " + SqlTablenames.user_access_uni_Table.COLUMN_NAME_ACCESS_UUID + " = " + UUID + ";";
            Wdb.execSQL(SQLquery);
        }
    }


    ///// DATA FROM DATABASE TO OBJECTS /////
    // university name to arraylist
    public static ArrayList<String> getUniNameFromDatabase() throws SQLException {
            ArrayList<String> uniList = new ArrayList<>();

        String rawQuery = "SELECT " + SqlTablenames.universitiesTable.COLUMN_NAME_NAME + " FROM " + SqlTablenames.universitiesTable.TABLE_NAME +";";
        Cursor cursor = Rdb.rawQuery(
                rawQuery,
                null
        );
        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(cursor.getColumnIndex(
                        SqlTablenames.universitiesTable.COLUMN_NAME_NAME
                ));

                uniList.add(name);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return uniList;
    }


    // userdata to user object
    public static List<User> getUsersFromDatabase() throws SQLException {
        List<User> userList = new ArrayList<>();

        Cursor cursor = Rdb.query(SqlTablenames.userTable.TABLE_NAME, null,
                null, null, null, null,
                SqlTablenames.userTable.COLUMN_NAME_USER_UUID);
        // TODO Jos ei toimi http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

        if (cursor.moveToFirst()) {
            do {
                int ID = cursor.getInt(cursor.getColumnIndex(
                        SqlTablenames.userTable.COLUMN_NAME_USER_UUID
                ));
                String userName = cursor.getString(cursor.getColumnIndex(
                        SqlTablenames.userTable.COLUMN_NAME_USERNAME
                ));
                String firstName = cursor.getString(cursor.getColumnIndex(
                        SqlTablenames.userTable.COLUMN_NAME_FIRSTNAME
                ));
                String surName = cursor.getString(cursor.getColumnIndex(
                        SqlTablenames.userTable.COLUMN_NAME_SURNAME
                ));
                String email = cursor.getString(cursor.getColumnIndex(
                        SqlTablenames.userTable.COLUMN_NAME_EMAIL
                ));
                String phoneNum = cursor.getString(cursor.getColumnIndex(
                        SqlTablenames.userTable.COLUMN_NAME_PHONE_NUMBER
                ));
                String pwdHash = cursor.getString(cursor.getColumnIndex(
                        SqlTablenames.userTable.COLUMN_NAME_PWD_HASH
                ));
                boolean admin = (cursor.getInt(cursor.getColumnIndex(
                        SqlTablenames.userTable.COLUMN_NAME_ADMINISTRATOR
                )) == 1);


                User user = new User();

                user.setUUID(ID);
                user.setUserName(userName);
                user.setFirstName(firstName);
                user.setSurName(surName);
                user.setEmail(email);
                user.setPhoneNum(phoneNum);
                user.setPasswordHash(pwdHash);
                user.setAdminPrivilege(admin);

                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return userList;
    }


    // sporthalldata to object
    public static List<Sporthall> getSporthallsFromDatabase() {
        List<Sporthall> hallList = new ArrayList<>();

        // tässä sporthalliim liitetään unin tiedot (nyt hakee kaikki sporthallin tiedot ja liittää niihin uni tablen niin että molemmissa uni id = 1)
        String rawQuery = "SELECT "+ SqlTablenames.sporthallTable.COLUMN_NAME_HALLID + ", " + SqlTablenames.sporthallTable.COLUMN_NAME_HALLNAME
                + ", " + SqlTablenames.universitiesTable.COLUMN_NAME_NAME + ", " + SqlTablenames.sporthallTable.COLUMN_NAME_HALLTYPE + ", " + SqlTablenames.sporthallTable.COLUMN_NAME_NOT_AVAILABLE
                + " FROM " + SqlTablenames.sporthallTable.TABLE_NAME + " INNER JOIN " + SqlTablenames.universitiesTable.TABLE_NAME
                + " ON " + SqlTablenames.universitiesTable.TABLE_NAME + "." + SqlTablenames.universitiesTable.COLUMN_NAME_UNI_UUID + " = "
                + SqlTablenames.sporthallTable.TABLE_NAME + "." + SqlTablenames.sporthallTable.COLUMN_NAME_UNI_UUID + ";";
        Cursor c = Rdb.rawQuery(
                rawQuery,
                null
        );

        if (c.moveToFirst()) {
            do {
                int ID = c.getInt(c.getColumnIndex(
                        SqlTablenames.sporthallTable.COLUMN_NAME_HALLID
                ));
                String name = c.getString(c.getColumnIndex(
                        SqlTablenames.sporthallTable.COLUMN_NAME_HALLNAME
                ));
                String uniname = c.getString(c.getColumnIndex(
                        SqlTablenames.universitiesTable.COLUMN_NAME_NAME
                ));
                String type = c.getString(c.getColumnIndex(
                        SqlTablenames.sporthallTable.COLUMN_NAME_HALLTYPE
                ));
                boolean notAvailable = (c.getInt(c.getColumnIndex(
                        SqlTablenames.sporthallTable.COLUMN_NAME_NOT_AVAILABLE
                )) == 1);

                Sporthall sporthall = new Sporthall();
                sporthall.setUUID(ID);
                sporthall.setName(name);
                sporthall.setUniversityName(uniname);
                sporthall.setType(type);
                sporthall.setDisabled(notAvailable);

                sporthall.updateReservationsFromSQL();

                hallList.add(sporthall);

            } while (c.moveToNext());
        }
        c.close();
        return hallList;
    }


    //get reservations from database
    public static List<Reservation> getReservationsFromDatabase(Sporthall sporthall) {
        List<Reservation> reservationList = new ArrayList<>();
        // TODO ajattelin että voisi hakea reservationit jokaiselle sporthall erikseen,
        // TODO säästäisi hieman prosessointiaikaa

        String[] sporthallID = {Integer.toString(sporthall.getUUID())}; // The id of the sporthall
        String whereClause = SqlTablenames.reservationsTable.COLUMN_NAME_HALLID + " = ?";

        Cursor cursor = Rdb.query(SqlTablenames.reservationsTable.TABLE_NAME,null,
                whereClause, sporthallID,
                null, null, null);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm");

        if (cursor.moveToFirst()) {
            do {

                int ID = cursor.getInt(cursor.getColumnIndex(
                        SqlTablenames.reservationsTable.COLUMN_NAME_RESERVEID
                ));
                String sport = cursor.getString(cursor.getColumnIndex(
                        SqlTablenames.reservationsTable.COLUMN_NAME_SPORT
                ));

                // CALENDAR PARSE STARTS
                Calendar startTime = Calendar.getInstance();
                try {
                    startTime.setTime(format.parse(cursor.getString(cursor.getColumnIndex(
                            SqlTablenames.reservationsTable.COLUMN_NAME_START_TIME
                    ))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // CALENDAR PARSE ENDS

                int duration = cursor.getInt(cursor.getColumnIndex(
                        SqlTablenames.reservationsTable.COLUMN_NAME_DURATION
                ));
                int ownerID = cursor.getInt(cursor.getColumnIndex(
                        SqlTablenames.reservationsTable.COLUMN_NAME_USER_UUID
                ));
                int maxPart = cursor.getInt(cursor.getColumnIndex(
                        SqlTablenames.reservationsTable.COLUMN_NAME_MAXPARTICIPANTS
                ));
                int recurEvent = cursor.getInt(cursor.getColumnIndex(
                        SqlTablenames.reservationsTable.COLUMN_NAME_RECURRING_EVENT
                ));


                Reservation reservation = new Reservation();
                reservation.setUUID(ID);
                reservation.setParent(sporthall);
                reservation.setSport(sport);
                reservation.setStartCalendar(startTime);
                reservation.setEndFromStartDur(startTime, duration);
                reservation.setOwner(ownerID);
                reservation.setMaxParticipants(maxPart);
                // TODO UPDATE ATTENDANCES
                // TODO RECURRING EVENT

                reservationList.add(reservation);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return reservationList;
    }


    // get enrolls from database
    public static List<Enroll> getEnrollsFromDatabase(Reservation reservation) throws SQLException {
        List<Enroll> enrollsList = new ArrayList<>();
        // TODO ajattelin että voisi hakea enrollit jokaiselle reservationille erikseen,
        // TODO säästäisi hieman prosessointiaikaa

        String[] reservationID = {Integer.toString(reservation.getUUID())}; // The id of the reservation
        String whereClause = SqlTablenames.enrollsTable.COLUMN_NAME_RESERVEID + " = ?";

        Cursor cursor = Rdb.query(SqlTablenames.reservationsTable.TABLE_NAME,null,
                whereClause, reservationID,
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int enrollID = cursor.getInt(cursor.getColumnIndex(
                        SqlTablenames.enrollsTable.COLUMN_NAME_ENROLLID
                ));
                int reserveID = cursor.getInt(cursor.getColumnIndex(
                        SqlTablenames.enrollsTable.COLUMN_NAME_RESERVEID
                ));
                int userUUID = cursor.getInt(cursor.getColumnIndex(
                        SqlTablenames.enrollsTable.COLUMN_NAME_USER_UUID
                ));


                Enroll enroll = new Enroll();

                enroll.setEnrollID(enrollID);
                enroll.setReserveID(reserveID);
                enroll.setUserUUID(userUUID);

                enrollsList.add(enroll);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return enrollsList;
    }


    /////////////////////////////////
    //PRESETTING VALUES TO DATABASE//
    /////////////////////////////////

    public static void presetDatabaseValues() {

        //User preset values
        String[] user = {"'admin'", "'admin'", "'admin'", "'admin.admin@adminmail.com'", "'0500628689'", "'" + PasswordManager.getHashedPassword("admin", "admin") + "'" , "1" };
        SQLuser.insertRow(user);
        user = new String[]{"'mattim'", "'Matti'", "'Meikäläinen'", "'Matti.Meikalainen@gmail.com'", "'0505689132'", "'" + PasswordManager.getHashedPassword("44mUj40nP4lJ0n", "mattim") + "'" , "0"};
        SQLuser.insertRow(user);
        user = new String[]{"'rickv'", "'Rick'", "'Vang'", "'Rick.Vang@webmail.com'", "'0290909857'", "'" + PasswordManager.getHashedPassword("AuR1nk#1n€n12?", "rickv") + "'" , "0"};
        SQLuser.insertRow(user);
        user = new String[]{"'jimb'", "'Jim'", "'Bass'", "'jimba89@gmail.com'", "'0440698602'", "'" + PasswordManager.getHashedPassword("H3LL0@w0rld!!!", "jimb") + "'" , "0"};
        SQLuser.insertRow(user);
        user = new String[]{"'jonh'", "'John'", "'Denton'", "'Jonny.Boii@memes.com'", "'0400568223'", "'" + PasswordManager.getHashedPassword("M€€mut0nK1v0ja", "jonh") + "'" , "0"};
        SQLuser.insertRow(user);
        user = new String[]{"'gregn'", "'Greg'", "'Novak'", "'Gregori.Nova@slavmail.ru'", "'0440666869'", "'" + PasswordManager.getHashedPassword("##N0tY0uR5But0ur5##", "gregn") + "'" , "0"};
        SQLuser.insertRow(user);
        user = new String[]{"'omarm'", "'Omar'", "'Marshall'", "'FieldMarshall@USAmail.com'", "'0500911420'", "'" + PasswordManager.getHashedPassword("M4k€US4Gr€4T4G4in", "omarm") + "'" , "0"};
        SQLuser.insertRow(user);
        user = new String[]{"'miac'", "'Mia'", "'Croft'", "'mia.croft@onlinemail.com'", "'0670884925'", "'" + PasswordManager.getHashedPassword("M€€mut0nK1v0ja", "miac") + "'" , "0"};
        SQLuser.insertRow(user);

        //Universities preset values
        SQLuniversities.insertRow("'LUT'", "'Yliopistonkatu 34, 53850 Lappeenranta'");

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
        String[] hall = { "'Gerpiili'", "1", "'Multipurpose'", "0" };
        SQLsporthall.insertRow(hall);
        hall = new String[]{ "'Kerpiili'", "1", "'Badminton'", "0" };
        SQLsporthall.insertRow(hall);
        hall = new String[]{ "'Kerbiili'", "1", "'Multipurpose'", "0" };
        SQLsporthall.insertRow(hall);
        hall = new String[]{ "'Gerbiili'", "1", "'Gym'", "0" };
        SQLsporthall.insertRow(hall);

        //Reservations preset values
        String[] reserved = { "2", "'Floorball'", "'2019-07-20T14:00'", "2", "2", "20", "0" };
        SQLreservation.insertRow(reserved);
        reserved = new String[]{ "2", "'Badminton'", "'2019-07-20T16:00'", "4", "3", "10", "0" };
        SQLreservation.insertRow(reserved);

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