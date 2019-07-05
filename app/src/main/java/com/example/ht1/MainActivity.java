package com.example.ht1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Help!");
        System.out.print("Kuinka committaan ja mergeän?\nJoko tämä toimii?");
    }
    public void login(View V){
        
    }
}
