package com.example.ht1;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ManageUsersFragment extends Fragment {
    private Button getReservations;
    private Button deleteUser;
    private Button deleteUsersReservation;
    private Button createButton;
    private EditText setNewUsername;
    private EditText setNewUserPassword;
    private Switch isAdmin;
    private Spinner userSpinner;
    private EditText setPhone;
    private EditText setEmail;
    private EditText firstname;
    private EditText surname;
    private EditText setUserUni;
    private TextView displayReservations;


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
        userSpinner = view.findViewById(R.id.Usersspinner_MUser);
        setEmail = view.findViewById(R.id.eSetEmailaddress_MUser);
        setPhone = view.findViewById(R.id.eSetPhone_MUser);
        firstname = view.findViewById(R.id.eNewUserFirsname_MUser);
        surname = view.findViewById(R.id.eNewUserSurname_MUser);
        displayReservations = view.findViewById(R.id.tSetEnrollsHere_MUser);
        setUserUni = view.findViewById(R.id.eUserUni_MUser);
        displayReservations.setMovementMethod(new ScrollingMovementMethod());
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
                updateUsersSpinner();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
        updateUsersSpinner();
        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getReservations();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    private void updateUsersSpinner() {
        List<String> users = new ArrayList<>();
        for (User user : SqlManager.getUsersFromDatabase()) {
            String text = user.getUserName();
            users.add(text);
            }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getView().getContext(),R.layout.support_simple_spinner_dropdown_item,users);
        userSpinner.setAdapter(adapter);
    }


    public void createUser() {
        String username = setNewUsername.getText().toString();
        String password = setNewUserPassword.getText().toString();
        String firsname = firstname.getText().toString();
        String Surname = surname.getText().toString(); //Android studio refused to accept variable "surname"
        String phone = setPhone.getText().toString();
        String email = setEmail.getText().toString();
        String Uniaccess = setUserUni.getText().toString();
        int adminAccount = 0;
        boolean adminUser = isAdmin.isChecked();
        boolean compliant = PasswordManager.passwordIsCompliant(password);
        String passwordhash = PasswordManager.getHashedPassword(password, username);

        if (adminUser == true){
            adminAccount = 1;
        }

        String adminAccountS = Integer.toString(adminAccount); //To String conversion for database
        String[] userinfo = {"'"+username+"'", "'"+firsname+"'", "'"+Surname+"'", "'"+phone+"'", "'"+email+"'", "'"+passwordhash+"'", adminAccountS};

        if (compliant) {
            for (User user : ReservationManager.usersList) {
                if (!username.equals(user.getUserName())) {
                    SqlManager.SQLuser.insertRow(userinfo);
                }
            }
            int user_uuid = 0;
            int uni_uuid = 0;
            user_uuid = SqlManager.getUserUUID(username);
            uni_uuid = SqlManager.getUniUUid(Uniaccess);
            String Uni_uuid = Integer.toString(uni_uuid);
            String User_uuid = Integer.toString(user_uuid);
            SqlManager.SQLaccess.insertRow(User_uuid,Uni_uuid);
            System.out.println(username + password + passwordhash + adminUser);
        } else {toast("Password is not compliant");}
        updateUsersSpinner();
    }


    public void getReservations(){
        //TODO get reservation and display them
        displayReservations.setText("");
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd kk:mm");
        int user_uuid = 0;

        for (User user : ReservationManager.usersList) {
            if(user.getUserName().equals(userSpinner.getSelectedItem())){
                user_uuid = user.getUUID();
            }
        }

        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            sporthall.updateReservationsFromSQL();
            for (Reservation reservation : sporthall.getReservations()) {
                if (reservation.getOwner().getUUID() == user_uuid) {
                    reservation.getAttenderList(reservation);
                    displayReservations.append(reservation.getSporthall().getName() + ", " + format.format(reservation.getStartDate().getTime()) + ", " + reservation.getSport() + ", " + reservation.getAttenderAmount() + " attenders\n");
                }
            }
        }
    }


    public void deleteUser(){
        SqlManager.SQLuser.removeRow("'" + userSpinner.getSelectedItem().toString() + "'");
        updateUsersSpinner();
    }


    public void deleteUsersreservation(){
        int user_uuid = 0;

        for (User user : ReservationManager.usersList) {
            if(user.getUserName().equals(userSpinner.getSelectedItem())){
                user_uuid = user.getUUID();
            }
        }

        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            sporthall.updateReservationsFromSQL();
            for (Reservation reservation : sporthall.getReservations()) {
                if (reservation.getOwner().getUUID() == user_uuid) {
                    SqlManager.SQLreservation.removeRow(Integer.toString(reservation.getUUID()));
                }
            }
        }
    }


    private void toast(String input) {
        Context context = getActivity();
        CharSequence text = input;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
