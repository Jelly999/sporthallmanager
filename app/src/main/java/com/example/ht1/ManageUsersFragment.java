package com.example.ht1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

//TODO: Fragment
public class ManageUsersFragment extends Fragment {
    private Button getReservations;
    private Button deleteUser;
    private Button deleteUsersReservation;
    private Button createButton;
    private EditText setNewUsername;
    private EditText setNewUserPassword;
    private Switch isAdmin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.manage_users, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getReservations = view.findViewById(R.id.bGetReservations_MUser);
        deleteUser = view.findViewById(R.id.bDeleteUser_MUser);
        deleteUsersReservation = view.findViewById(R.id.bDeleteUsersReservation_MUser);
        createButton = view.findViewById(R.id.bAddNewUser_MUser);
        setNewUsername = view.findViewById(R.id.eNewUsername_MUser);
        setNewUserPassword = view.findViewById(R.id.eNewPassword_MUser);
        isAdmin = view.findViewById(R.id.sIsAdmin);
        getReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getReservations();
            }
        });
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });
        deleteUsersReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUsersreservation();
            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    public void createUser() {
        String username = setNewUsername.getText().toString();
        String password = setNewUserPassword.getText().toString();
        boolean adminUser = isAdmin.isChecked();
        boolean compliant = PasswordManager.passwordIsCompliant(password);
        String passwordhash = PasswordManager.getHashedPassword(password, username);
        if (compliant) {
            //TODO input data to database
            System.out.println(username + password + passwordhash + adminUser);
        } else {toast("Password is not compliant");}
    }
    public void getReservations(){
        //TODO get reservation and display them
    }
    public void deleteUser(){
        //TODO delete user from database
    }
    public void deleteUsersreservation(){
        //TODO delete user's reservations from database
    }
    private void toast(String input) {
        Context context = getActivity();
        CharSequence text = input;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
