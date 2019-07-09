package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Help!");

        // TODO tämä tässä vain testiä varten
        JSONManager jsonManager = new JSONManager(this);
        jsonManager.JSONTEST();
        // Testi loppu
    }
    public void login(View V){
        
    }
}
