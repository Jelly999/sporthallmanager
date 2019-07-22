package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EnrolledFragment extends Fragment {
    TextView scrollableText;
    List<Enroll> enrollList;
    List<Reservation> reservationsList;
    List<Reservation> AllReservationsList;
    List<Reservation> ReservtionToEnrollList;
    List<Sporthall> sporthallList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.enrolled, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'kk:mm");
        enrollList = SqlManager.getEnrollsFromDatabase();
        scrollableText = getView().findViewById(R.id.Scrollable);
        scrollableText.setText("");
        ReservtionToEnrollList = getAllReservation();
        System.out.println(ReservtionToEnrollList);
        for (Enroll enroll : enrollList) {
            if (enroll.getUserUUID() == User.getCurrentUser().getUUID()) {
                for (Reservation reservation : ReservtionToEnrollList) {
                    if (enroll.getReserveID() == reservation.getUUID()) {
                        reservation.getAttenderList(reservation);
                        String an_enroll = reservation.getSporthall().getName() + ", " + format.format(reservation.getStartDate().getTime()) + ", " + reservation.getSport() + ", " + reservation.getAttenderAmount();
                        scrollableText.append("\n" + an_enroll + "\n");
                    }
                }
            }
        }
    }

    public List<Reservation> getAllReservation() {
        AllReservationsList = new ArrayList<>();
        //sporthallList = ReservationManager.sporthallsList;
        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            //reservationsList = SqlManager.getReservationsFromDatabase(sporthall);
            for (Reservation reservation : SqlManager.getReservationsFromDatabase(sporthall)) {
                AllReservationsList.add(reservation);
            }
        }
        return AllReservationsList;
    }
}
