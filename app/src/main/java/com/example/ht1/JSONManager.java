package com.example.ht1;

//TODO Luokka, joka tallentaa salien varaustiedot XML tai JSON tiedostomuodossa

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class JSONManager {

    private Context context;

    JSONManager(Context _context) {
        context = _context;
    }

    public void JSONTEST() {
        try {
            JSONObject object = JSONEndocing();
            writeJSONToStorage(object);
        } catch (Exception e) { //TODO Oisko parempi virheen hallinta?
            e.printStackTrace();
        }

    }

    // TODO Toistaiseksi vain testi-tilanteen tallennus
    public JSONObject JSONEndocing() throws JSONException {
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


    // ======= PRIVATE METHODS =======

    private boolean writeJSONToStorage(JSONObject object) {
        if (isExternalStorageWritable()) {
            Log.d("JSON", context.getExternalFilesDir(null).toString());
            try {
                writeToFile(object.toString(4), "JSON_testi.txt");
            } catch (JSONException e) { //TODO Oisko parempi virheen hallinta?
                e.printStackTrace();
            }
        }
        return false;
    }


    private void writeToFile(String data, String filename) {
        try {
            OutputStreamWriter OSW = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            OSW.write(data);
            OSW.close();
        } catch (Exception e) { //TODO Oisko parempi virheen hallinta?
            e.printStackTrace();
        }
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
