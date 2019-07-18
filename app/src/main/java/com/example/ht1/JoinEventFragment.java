package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class JoinEventFragment extends Fragment {

    private TextView sporteventInfoText;
    private Spinner sporteventsSpinner;
    private Button joinButton;
    private Button cancelButton;


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
    }

    private void updateSporteventSpinner() {
        List<String> reservationStrings = new ArrayList<>();
        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            if (!sporthall.getDisabled()) {
                for (Reservation reservation : sporthall.getReservations()) {
                    String text = reservation.getSport();
                    text += " " + reservation.getStartDate().toString();
                    reservationStrings.add(text);
                }
            }
        }
    }

}
