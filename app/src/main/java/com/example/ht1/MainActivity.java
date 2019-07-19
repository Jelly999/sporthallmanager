package com.example.ht1;


import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

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

        launchlogin();

        // TODO THIS IS HOW TO CALL JSON
        JSONManager jsonManager = new JSONManager(this);
        jsonManager.JSONTEST();
    }

    @Override
    public void onBackPressed() {
        //System.out.println("Tryna go back.");
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (f instanceof LoginFragment) {
            Log.d("ONBACK", "Login fragment it is");
            finish();
        } else if (f instanceof authFragment) {
            Log.d("ONBACK", "Authenticator it is");
            finish();
        } else if (f instanceof MainMenuFragment) {
            Log.d("ONBACK", "Main menu it is");
            finish();
        } else {
            Log.d("ONBACK", "popBackStack");
            getSupportFragmentManager().popBackStackImmediate();
        }
    }
    private void toast(){
        Context context = getApplicationContext();
        CharSequence text = "You do not have administrative permissions!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text, duration);
        toast.show();
    }

    public void popStacks() {
        Log.d("ONBACK", "popBackStack on command");
        getSupportFragmentManager().popBackStackImmediate();
    }
    public void logOut(View v){
        User.setCurrentUser(null);
        launchlogin();
    }

    public void launchMainMenu() {
        MainMenuFragment joinevent = new MainMenuFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, joinevent);
        fragmentTransaction.commit();
    }

    public void launchlogin() {
        LoginFragment login = new LoginFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, login);
        fragmentTransaction.commit();
    }
    public void launchAuth() {
        authFragment authentication = new authFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, authentication);
        fragmentTransaction.commit();
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

    public void GotoMainMenu(View v) {
        popStacks();
    }


    public void saveHalldata(View v) throws Exception{
        EditText input = findViewById(R.id.eNewHallname_MHalls);
        String Hall_name = input.getText().toString();
        input = findViewById(R.id.eNewHallLocation_MHalls);
        String Hall_Location = input.getText().toString();
        input = findViewById(R.id.eNewUsername_MUser);
        String Hall_type = input.getText().toString();
        //TODO Save to database
        System.out.println(Hall_name+Hall_Location+Hall_type);
    }


    public void account(View v){
        AccountFragment account = new AccountFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, account);
        fragmentTransaction.commit();

        //TODO go to account fragmnent, User can click
    }
    public void joinEvent(View v){
        JoinEventFragment joinevent = new JoinEventFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, joinevent);
        fragmentTransaction.commit();
        //TODO go to join event fragmnent, User can click
    }
    public void creteNewEvent(View v){
        CreateEventFragment createevent = new CreateEventFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, createevent);
        fragmentTransaction.commit();
        //TODO go to create new event fragmnent, User can click
    }
    public void viewEnrolled(View v){
        EnrolledFragment enrolled = new EnrolledFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, enrolled);
        fragmentTransaction.commit();
        //TODO go to enrolled fragmnent, User can click
    }
    public void editEvent(View v){
        EventEditFragment eventedit = new EventEditFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, eventedit);
        fragmentTransaction.commit();
        //TODO go to edit event fragmnent, User can click
    }
    public void manageUsers(View v){
        boolean value = User.getCurrentUser().isAdmin();
        if (value) {
        ManageUsersFragment manage_users = new ManageUsersFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, manage_users);
        fragmentTransaction.commit();
        }else{toast();}
        //TODO go to manage users fragmnent, Only For Admin to click
    }
    public void manageHalls(View v){
        boolean value = User.getCurrentUser().isAdmin();
        if (value) {
            ManageHallsFragment manage_hall = new ManageHallsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragment_container, manage_hall);
            fragmentTransaction.commit();
        }else{toast();}
        //TODO go to manage hall fragmnent, Only For Admin to click
    }
    public void manageUni(View v){
        boolean value = User.getCurrentUser().isAdmin();
        if (value) {
        ManageUniFragment manage_uni = new ManageUniFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, manage_uni);
        fragmentTransaction.commit();
        }else{toast();}
        //TODO go to manage uni fragmnent, Only For Admin to click
    }
}
