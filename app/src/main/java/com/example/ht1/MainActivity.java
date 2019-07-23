package com.example.ht1;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;

public class MainActivity extends AppCompatActivity {
    private ReservationManager reservationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        launchlogin(); //This launches first fragment

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
    public void JSONcall(View v){
        JSONManager jsonManager = new JSONManager(this);
        jsonManager.JSONTEST();
    }
    public void CSVcall(View v){
        //TODO csv
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

    public void account(View v){ //account fragment launch
        AccountFragment account = new AccountFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, account);
        fragmentTransaction.commit();
    }
    public void joinEvent(View v){//Join event fragment launch
        JoinEventFragment joinevent = new JoinEventFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, joinevent);
        fragmentTransaction.commit();
    }
    public void creteNewEvent(View v){//Create event fragment launch
        CreateEventFragment createevent = new CreateEventFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, createevent);
        fragmentTransaction.commit();
    }
    public void viewEnrolled(View v){ //View enrolled fragment launch
        EnrolledFragment enrolled = new EnrolledFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, enrolled);
        fragmentTransaction.commit();
    }
    public void editEvent(View v){ //Edit event fragment launch
        EventEditFragment eventedit = new EventEditFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, eventedit);
        fragmentTransaction.commit();
    }
    public void manageUsers(View v){ //Manage users fragment launch
        boolean value = User.getCurrentUser().isAdmin();
        if (value) {
        ManageUsersFragment manage_users = new ManageUsersFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, manage_users);
        fragmentTransaction.commit();
        }else{toast();}
    }
    public void manageHalls(View v){ //Manage halls fragment launch
        boolean value = User.getCurrentUser().isAdmin();
        if (value) {
            ManageHallsFragment manage_hall = new ManageHallsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.fragment_container, manage_hall);
            fragmentTransaction.commit();
        }else{toast();}
    }
    public void manageUni(View v){ //Manage uni fragment launch
        boolean value = User.getCurrentUser().isAdmin();
        if (value) {
        ManageUniFragment manage_uni = new ManageUniFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, manage_uni);
        fragmentTransaction.commit();
        }else{toast();}
    }
}
