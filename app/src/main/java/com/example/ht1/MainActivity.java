package com.example.ht1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

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
        JSONManager jsonManager = new JSONManager(this);
        jsonManager.JSONTEST();
        PasswordManager passwordManager = new PasswordManager();
        passwordManager.hashTest();
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

            mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new ExampleAdapter(exampleList);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        } //Recycle view test

    }
    public void login(View V){
        
    }
}
