package com.example.ht1;

//TODO Luokka, joka tallentaa salien varaustiedot XML tai JSON tiedostomuodossa

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

public class JSONManager {

    private Context context;

    JSONManager(Context _context) {
        context = _context;
    }

    public void JSONTEST() {
        try {
            JSONObject object = JSONEndocing();
            writeJSONToStorage(object, "json_test");
        } catch (Exception e) { //TODO Oisko parempi virheen hallinta?
            e.printStackTrace();
        }

    }


    public void saveReservationsCSV(Reservation[] reservations, String fileName) {
        String data = "";
        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd.mm.yyyy 'at' hh:mm");

        for (Reservation reserv : reservations) {
            data += reserv.getUUID() + ",";
            data += reserv.getTitle() + ",";
            data += reserv.getSporthall().getName() + ",";
            data += reserv.getSport() + ",";
            data += reserv.getOwner().getUserName() + ",";
            data += format.format(reserv.getStartDate()) + ",";
            data += format.format(reserv.getEndDate()) + ",";

            String attenders = "";
            for (User user : reserv.getAttenderList()) {
                attenders += user.getUserName() + ",";
            }
            data += attenders + ";";
        }

        writeToFile(data, fileName);
    }

    // TODO Toistaiseksi vain testi-tilanteen tallennus
    private JSONObject JSONEndocing() throws JSONException {
        JSONObject user_1 = new JSONObject();
        user_1.put("UUID", Integer.valueOf(1));
        user_1.put("userName", "Jussi Mäkelä");
        user_1.put("admin", false);
        user_1.put("disabled", false);

        JSONObject user_2 = new JSONObject();
        user_2.put("UUID", Integer.valueOf(2));
        user_2.put("userName", "Sanna Kuusivaara");
        user_2.put("admin", true);
        user_2.put("disabled", false);

        JSONObject user_3 = new JSONObject();
        user_3.put("UUID", Integer.valueOf(3));
        user_3.put("userName", "Antti Virveli");
        user_3.put("admin", false);
        user_3.put("disabled", false);

        JSONArray usersArray = new JSONArray();
        usersArray.put(user_1);
        usersArray.put(user_2);
        usersArray.put(user_3);

        //JSONObject usersObj = new JSONObject();
        //usersObj.put("users", usersArray);

        // SPORTHALL

        JSONObject sportsHall_1 = new JSONObject();
        sportsHall_1.put("UUID", Integer.valueOf(1));
        sportsHall_1.put("name", "Monari");
        sportsHall_1.put("maximumCapacity", Integer.valueOf(64));
        sportsHall_1.put("disabled", Boolean.valueOf(false));
        sportsHall_1.put("universityName", "Lappeenrannan Lahden teknillinen yliopisto LUT University");
        sportsHall_1.put("streetAdress", "En jaksa googlaa");

        // SPORTHALL RESERVATIONS

        JSONArray reservArray_1 = new JSONArray();

        JSONObject reserv_1 = new JSONObject();
        reserv_1.put("UUID", Integer.valueOf(1));
        reserv_1.put("title", "Jumppa");
        reserv_1.put("sporthall", sportsHall_1.get("UUID")); // UUID of the sporthall
        reserv_1.put("describtion", "Piirileikkejä");
        reserv_1.put("owner", user_1.get("UUID"));
        // TODO Calendar dates go here
        JSONArray reserv_1_attenders = new JSONArray();
        reserv_1_attenders.put(user_2.get("UUID"));
        reserv_1.put("attenderList", reserv_1_attenders);

        reservArray_1.put(reserv_1);

        sportsHall_1.put("reservationsList", reservArray_1);


        // SYSTEM

        JSONArray sporthallsList = new JSONArray();
        sporthallsList.put(sportsHall_1);

        JSONObject main = new JSONObject();
        main.put("users", usersArray);
        main.put("sportHalls", sporthallsList);

        return main;
    }



    // ======= PRIVATE METHODS OBJECTS =======

    private JSONObject attendersJSON(Reservation reservation) {
        JSONArray attenders = new JSONArray();
        for (User user : reservation.getAttenderList()) {
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
        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd.mm.yyyy 'at' hh:mm");

        JSONObject reservObj = new JSONObject();
        try {
            reservObj.put("UUID", reservation.getUUID());
            reservObj.put("title", reservation.getTitle());
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
        } catch (JSONException e) { //TODO Oisko parempi virheen hallinta?
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
            } catch (Exception e) { //TODO Oisko parempi virheen hallinta?
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
