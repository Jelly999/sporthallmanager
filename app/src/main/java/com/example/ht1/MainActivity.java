package com.example.ht1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
        ArrayList<item> exampleList = new ArrayList<>();
        exampleList.add(new item(R.drawable.ic_android, "Line 1", "Line 2"));
        exampleList.add(new item(R.drawable.ic_android, "Line 3", "Line 4"));
        exampleList.add(new item(R.drawable.ic_android, "Line 5", "Line 6"));
    }
    public void login(View V){
        
    }
}
