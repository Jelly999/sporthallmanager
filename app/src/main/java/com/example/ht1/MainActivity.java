package com.example.ht1;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
        sqlTest();
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


    }

    public boolean databaseExists() {
        //String path = this.getDir("databases",Context.MODE_PRIVATE).toString();
        String path = this.getFilesDir().getPath();
        path = path.substring(0,28);
        path += "/databases";
        path += "/sporthallmanager.db";
        File file = new File(path);
        Log.d("FILE", path);
        return file.exists();
    }

    public static void sqlTest() { // TODO DELETE ONCE TEST OVER
        List<User> userList = SqlManager.getUsersFromDatabase();
        Log.d("SQL", "Number of users: " + userList.size());
        for (User user : userList) {
            Log.d("SQL", user.toString());
        }
    }

    public void login(View V){
        System.out.println("login");
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
}
