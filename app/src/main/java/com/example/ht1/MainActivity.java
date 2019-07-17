package com.example.ht1;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ReservationManager reservationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Help!");
        //CardView card = new CardView();

        // TODO tämä tässä vain testiä varten
        if (databaseExists()) {
            Log.d("FILE", "ON OLEMASSA!");
            new SqlManager(this);
        } else {
            Log.d("FILE", "EI OLE OLEMASSA");
            new SqlManager(this); // This must be after the existance of the file is checked
            SqlManager.presetDatabaseValues();
        }
        objectInitalizationTest();
        // Testi loppu
        {
            ArrayList<ExampleItem> exampleList = new ArrayList<>();
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 1", "Line 2"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 3", "Line 4"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 5", "Line 6"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 7", "Line 8"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 9", "Line 10"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 11", "Line 12"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 13", "Line 14"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 15", "Line 16"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 17", "Line 18"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 19", "Line 20"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 21", "Line 22"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 23", "Line 24"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 25", "Line 26"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 27", "Line 28"));
            exampleList.add(new ExampleItem(R.drawable.ic_android, "Line 29", "Line 30"));
/*
            mRecyclerView = findViewById(R.id.recyclerView);
            //mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new ExampleAdapter(exampleList);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
*/        } //Recycle view test

    //TODO launch login fragment

    }

    public boolean databaseExists() {
        String path = this.getFilesDir().getPath();
        path = path.substring(0,28);
        path += "/databases";
        path += "/sporthallmanager.db";
        File file = new File(path);
        Log.d("FILE", path);
        return file.exists();
    }

    public void objectInitalizationTest() { // TODO DELETE ONCE TEST OVER
        reservationManager = new ReservationManager();
        reservationManager.logAllUsers("OBJECT");
        reservationManager.logAllSporthalls("OBJECT");
        reservationManager.logAllReservations("OBJECT");
    }

    public void login(View V){
        EditText input = findViewById(R.id.eUsername_login);
        String username = input.getText().toString();
        input = findViewById(R.id.eUsername_login);
        String password = input.getText().toString();
        String pwdhash = PasswordManager.getHashedPassword(password, username);
        String pwdhashdb = "";//getPasswordHash(username); // Get password hash from database
        boolean loginSuccess = false;
        if (pwdhash == pwdhashdb){
            //Go to Authenticator fragment
            String authNumbers = PasswordManager.authNumbers();
            TextView output = findViewById(R.id.text_randint_auth);
            output.setText(authNumbers);
            EditText rinput = findViewById(R.id.edit_inputint_auth);
            String numbers = rinput.getText().toString();
            if (authNumbers == numbers){
                loginSuccess = true;
            }
        }
        if (loginSuccess == true){
            //On login button press --> Go to main menu
        }
    }

    public void account(View v){
        //TODO go to account fragmnent, User can click
        System.out.println("account");
    }
    public void joinEvent(View v){
        //TODO go to join event fragmnent, User can click
    }
    public void creteNewEvent(View v){
        //TODO go to create new event fragmnent, User can click
    }
    public void viewEnrolled(View v){
        //TODO go to enrolled fragmnent, User can click
    }
    public void editEvent(View v){
        //TODO go to edit event fragmnent, User can click
    }
    public void manageUsers(View v){
        //TODO go to manage users fragmnent, Only For Admin to click
    }
    public void manageHalls(View v){
        //TODO go to manage hall fragmnent, Only For Admin to click
    }
    public void manageUni(View v){
        //TODO go to manage uni fragmnent, Only For Admin to click
    }



    public void getHallReservations(View v){
        //TODO Show selected hall reservations in View
    }
    public void deleteHall(View v){
        //TODO Delete selected hall
    }
    public void enableHall(View v){
        //TODO save hall
    }
    public void disableHall(View v){
        //TODO disable hall
    }
    public void saveHall(View v){
        //TODO save new hall and go to main menu fragment
    }
    public void cancelHall(View v){
        //TODO go to main menu fragment
    }
}
