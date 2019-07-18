package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JoinEventFragment extends Fragment {

    private TextView sporteventInfoText;
    private Spinner sporteventsSpinner;
    private Button joinButton;
    private Button cancelButton;
    SimpleDateFormat format;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.joinevent, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        sporteventInfoText = view.findViewById(R.id.tSporteventInfo_join);
        sporteventsSpinner = view.findViewById(R.id.sporteventSpinner_join);
        joinButton = view.findViewById(R.id.bJoinEvent);
        cancelButton = view.findViewById(R.id.bCancel_join);
        format = new SimpleDateFormat("yyyy.MM.dd kk:mm");

        updateSporteventSpinner();
        sporteventsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateInfoBox(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Yeet
            }
        });
        sporteventInfoText.setText("Yehaw!");
    }

    public void JoinAnEvent(View v) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'kk:mm");
        List<Reservation> reservationsList;
        String spinner = sporteventsSpinner.getSelectedItem().toString();
        System.out.println(spinner);
        reservationsList = getEventFromList();
        for (Reservation reservation : reservationsList) {
            String reservationData = reservation.getSport() + " " + format.format(reservation.getStartDate().getTime());
            System.out.println(reservationData);
            if (reservationData.equals(spinner)) {
                List<User> attenders = reservation.getAttenderList();
                for (User user : attenders) {
                    if (user.getUUID() != User.getCurrentUser().getUUID()) {
                        System.out.println("Nyt osallistutaan");

                    }
                }
            }
        }

        //SqlManager.SQLenrolls.insertRow(User.getCurrentUser().getUUID(),);
    }

    private List<Reservation> getEventFromList() {
        List<Reservation> reservations = new ArrayList<>();
        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            if (!sporthall.getDisabled()) {
                for (Reservation reservation : sporthall.getReservations()) {
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }

    private void updateSporteventSpinner() {


        List<String> reservationStrings = new ArrayList<>();
        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            if (!sporthall.getDisabled()) {
                for (Reservation reservation : sporthall.getReservations()) {
                    String text = reservation.getSport();
                    text += "  " + format.format(reservation.getStartDate().getTime());
                    reservationStrings.add(text);
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getView().getContext(),R.layout.support_simple_spinner_dropdown_item,reservationStrings);
        sporteventsSpinner.setAdapter(adapter);
    }

    private void updateInfoBox(int i) {
        Reservation reservation = getReservationFromPosition(i);
        if (reservation != null) {
            String text = "";
            text += "Sport type: " + reservation.getSport() + "\n";
            text += "Sporthall name: " + reservation.getSporthall().getName() + "\n";
            text += "Owner: " + reservation.getOwner().getFirstName() + " " + reservation.getOwner().getSurName() + "\n";
            text += "Start time: " + format.format(reservation.getStartDate().getTime()) + "\n";
            text += "End time: " + format.format(reservation.getEndDate().getTime()) + "\n";
            text += "Attenders: " + reservation.getAttenderAmount() + "\n";
            text += "Max attenders: " + reservation.getMaxParticipants() + "\n";

            sporteventInfoText.setText(text);
        }
    }

    private Reservation getReservationFromPosition(int i) {
        int j = 0;
        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            if (!sporthall.getDisabled()) {
                for (Reservation reservation : sporthall.getReservations()) {
                    if (j == i) {
                        return reservation;
                    } else {
                        j++;
                    }
                }
            }
        }
        return null;
    }

}
