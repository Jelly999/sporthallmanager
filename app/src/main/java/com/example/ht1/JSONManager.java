package com.example.ht1;

//TODO Luokka, joka tallentaa salien varaustiedot XML tai JSON tiedostomuodossa

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JSONManager {

    private Context context;

    JSONManager(Context _context) {
        context = _context;
    }

    private SimpleDateFormat format = new SimpleDateFormat("EEEE, dd.mm.yyyy 'at' hh:mm");;


    public void JSONTEST() {
        try {
            JSONObject object = JSONEndocing();
            writeJSONToStorage(object, "json_test.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void saveReservationsCSV(Reservation[] reservations, String fileName) {

        //public class CsvFileWriter {

            /*//Delimiter used in CSV file
            private static final String COMMA_DELIMITER = ",";
            private static final String NEW_LINE_SEPARATOR = "\n";*/

            //CSV file header
            final String FILE_HEADER = "reserveid,hallname,sport,owner,start_time,end_time";

            /*public static void writeCsvFile(String fileName) {

                Student student1 = new Student(1, "Ahmed", "Mohamed", "M", 25);
                Student student2 = new Student(2, "Sara", "Said", "F", 23);
                Student student3 = new Student(3, "Ali", "Hassan", "M", 24);
                Student student4 = new Student(4, "Sama", "Karim", "F", 20);
                Student student5 = new Student(5, "Khaled", "Mohamed", "M", 22);
                Student student6 = new Student(6, "Ghada", "Sarhan", "F", 21);

                List students = new ArrayList();
                students.add(student1);
                students.add(student2);
                students.add(student3);
                students.add(student4);
                students.add(student5);
                students.add(student6);*/

            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(fileName);

                fileWriter.append(FILE_HEADER.toString());

                fileWriter.append("\n");

                String data = "";
                SimpleDateFormat format = new SimpleDateFormat("EEEE, dd.mm.yyyy 'at' hh:mm");

                for (Reservation reserv : reservations) {
                    data += reserv.getUUID() + ",";
                    data += reserv.getSporthall().getName() + ",";
                    data += reserv.getSport() + ",";
                    data += reserv.getOwner().getUserName() + ",";
                    data += format.format(reserv.getStartDate().getTime()) + ",";
                    data += format.format(reserv.getEndDate().getTime()) + ",";

                    String attenders = "";
                    for (User user : reserv.getAttenderList(reserv)) {
                        attenders += user.getUserName() + ",";
                    }
                    data += attenders + "\n";
                }
                    /*for (Student student : students) {
                        fileWriter.append(String.valueOf(student.getId()));
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(student.getFirstName());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(student.getLastName());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(student.getGender());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(String.valueOf(student.getAge()));
                        fileWriter.append(NEW_LINE_SEPARATOR);
                    }*/
            } catch (Exception e) {
                System.out.println("Error in CsvFileWriter !!!");
                e.printStackTrace();
            } finally {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error while flushing/closing fileWriter !!!");
                    e.printStackTrace();
                }
            }

        /*String data = "";
        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd.mm.yyyy 'at' hh:mm");

        for (Reservation reserv : reservations) {
            data += reserv.getUUID() + ",";
            data += reserv.getSporthall().getName() + ",";
            data += reserv.getSport() + ",";
            data += reserv.getOwner().getUserName() + ",";
            data += format.format(reserv.getStartDate().getTime()) + ",";
            data += format.format(reserv.getEndDate().getTime()) + ",";

            String attenders = "";
            for (User user : reserv.getAttenderList(reserv)) {
                attenders += user.getUserName() + ",";
            }
            data += attenders + "\n";
        }

        writeToFile(data, fileName);*/
    }


    // TODO Toistaiseksi vain testi-tilanteen tallennus
    private JSONObject JSONEndocing() throws JSONException {
        /*JSONObject user_1 = new JSONObject();
        user_1.put("UUID", Integer.valueOf(1));
        user_1.put("userName", "Jussi Mäkelä");
        user_1.put("admin", false);
        user_1.put("disabled", false);*/

        JSONArray usersArray = new JSONArray();
        for (User user : ReservationManager.usersList) {
            JSONObject user_1 = new JSONObject();
            user_1.put("UUID", user.getUUID());
            user_1.put("userName", user.getUserName());
            user_1.put("firstName", user.getFirstName());
            user_1.put("surname", user.getSurName());
            user_1.put("Email", user.getEmail());
            user_1.put("phoneNumber", user.getPhoneNum());
            user_1.put("passwordHash", user.getPasswordHash());
            user_1.put("isAdministrator", user.isAdmin());
            usersArray.put(user_1);
        }

        // SPORTHALL

        JSONArray sportHallArray = new JSONArray();
        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            JSONObject sportJson = new JSONObject();
            sportJson.put("HALLID", sporthall.getUUID());
            sportJson.put("hallName", sporthall.getName());
            sportJson.put("uniID", sporthall.getUniversityName());
            sportJson.put("hallType", sporthall.getType());
            sportJson.put("disabled", sporthall.getDisabled());

            JSONArray reservArray = new JSONArray();
            for (Reservation reservation : sporthall.getReservations()) {
                JSONObject reservJson = new JSONObject();
                reservJson.put("RESERVEID", reservation.getUUID());
                reservJson.put("sport", reservation.getSport());
                reservJson.put("startTime", format.format(reservation.getStartDate().getTime()));
                reservJson.put("endTime", format.format(reservation.getEndDate().getTime()));
                reservJson.put("ownerID", reservation.getOwner().getUUID());
                reservJson.put("maxParticipants", reservation.getMaxParticipants());
                //reservJson.put("reccuring", ) TODO GET RECCURING EVENT!!
                //Log.d("JSON", "Reservation added");

                JSONArray attenderArray = new JSONArray();
                for (User user : reservation.getAttenderList(reservation)) {
                    JSONObject attend = new JSONObject();
                    attend.put("userID", user.getUUID());
                    attend.put("firstName", user.getFirstName());
                    attend.put("surname", user.getSurName());
                    attenderArray.put(attend);
                }
                reservJson.put("attenders", attenderArray);
                reservArray.put(reservJson);
            }
            sportJson.put("reservations", reservArray);
            sportHallArray.put(sportJson);
        }

        // SPORTHALL RESERVATIONS

        /*JSONArray reservArray_1 = new JSONArray();

        JSONObject reserv_1 = new JSONObject();
        reserv_1.put("UUID", Integer.valueOf(1));
        reserv_1.put("title", "Jumppa");
        reserv_1.put("sporthall", sportsHall_1.get("UUID")); // UUID of the sporthall
        reserv_1.put("describtion", "Piirileikkejä");
        reserv_1.put("owner", user_1.get("UUID"));
        JSONArray reserv_1_attenders = new JSONArray();
        reserv_1_attenders.put(user_2.get("UUID"));
        reserv_1.put("attenderList", reserv_1_attenders);

        reservArray_1.put(reserv_1);

        sportsHall_1.put("reservationsList", reservArray_1);*/


        // SYSTEM

        JSONObject main = new JSONObject();
        main.put("users", usersArray);
        main.put("sportHalls", sportHallArray);

        return main;
    }


    // ======= PRIVATE METHODS OBJECTS =======

    private JSONObject attendersJSON(Reservation reservation) {
        JSONArray attenders = new JSONArray();
        for (User user : reservation.getAttenderList(reservation)) {
            try {
                JSONObject attender = new JSONObject();
                attender.put("name", user.getUserName());
                attender.put("UUID", user.getUUID());
                attenders.put(attender);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject main = new JSONObject();
        try {
            main.put("attenders", attenders);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return main;
    }


    public JSONObject getReservationJSON(Reservation reservation, String fileName) {
        //SimpleDateFormat format = new SimpleDateFormat("EEEE, dd.mm.yyyy 'at' hh:mm");

        JSONObject reservObj = new JSONObject();
        try {
            reservObj.put("UUID", reservation.getUUID());
            reservObj.put("sporthall", reservation.getSporthall().getName());
            reservObj.put("describtion", reservation.getSport());
            reservObj.put("owner", reservation.getOwner());
            String startDate = format.format(reservation.getStartDate());
            String endDate = format.format(reservation.getEndDate());
            reservObj.put("start_date", startDate);
            reservObj.put("end_date", endDate);
            reservObj.put("attenders", attendersJSON(reservation));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reservObj;
    }


    // ======= METHODS FILE MANAGEMENT =======

    private boolean writeJSONToStorage(JSONObject object, String fileName) {
        Log.d("JSON", context.getExternalFilesDir(null).toString());
        try {
            writeToFile(object.toString(4), fileName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void writeToFile(String data, String filename) {
        if (isExternalStorageWritable()) {
            try {
                OutputStreamWriter OSW = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
                OSW.write(data);
                OSW.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }


    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
}
