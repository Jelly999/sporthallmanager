package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Get values from edit fields
        EditText usrname = (EditText) view.findViewById(R.id.editLoginName);
        String username = usrname.getText().toString();
        EditText pwd = (EditText) view.findViewById(R.id.editLoginPwd);
        String password = pwd.getText().toString();

        //check if login is successful
        loginSuccess(username, password);

        //Compare hash
    }

    public boolean loginSuccess(String username, String password) {
        // Get username from db
        String usernamedb = "";
        String pwdhashdb = ""; //Get password hash from db
        String pwdhash = "password"; // Create hash for comparison

        //
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // is username in db
        if (username == usernamedb) {
            // does the password match
            if (pwdhash == pwdhashdb) {
                return true;
            }else;
                return false;
        }else;
            return false;
    }



}