package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class authFragment extends Fragment {

    private EditText userNameInput;
    private EditText passwordInput;
    private TextView randomint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.authentication, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    /*public void authbutton(View v){
        System.out.println("auth button");
        String authNumbers = "";
        EditText rinput = findViewById(R.id.edit_inputint_auth);
        String numbers = rinput.getText().toString();
        if (authNumbers != numbers) {
            launchMainMenu();
        }
    }*/
}
