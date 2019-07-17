package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PSSParameterSpec;


public class LoginFragment extends Fragment {

    private EditText userNameInput;
    private EditText passwordInput;
    private Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Get values from edit fields
        userNameInput = view.findViewById(R.id.eUsername_login);
        passwordInput = view.findViewById(R.id.ePassowrd_login);
        loginButton = view.findViewById(R.id.bLogin_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButtonClicked();
            }
        });
    }

    private void loginButtonClicked() {
        String userName = userNameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String pswHash = PasswordManager.getHashedPassword(password, userName);

        User loginUser = isCredintialsOK(userName, pswHash);

        if (loginUser != null) {
            System.out.println("Login accepted");
            User.setCurrentUser(loginUser);

            ((MainActivity)getActivity()).launchAuth();

        } else {
            System.out.println("Login denied");
        }

        System.out.println(userName + "  " + password);
    }

    private User isCredintialsOK(String userName, String passwordHash) {

        for (User user : ReservationManager.usersList) {
            if (user.getUserName().equals(userName)) {
                if (user.getPasswordHash().equals(passwordHash)) {
                    return user;
                }
            }
        }
        return null;
    }
}