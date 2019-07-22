package com.example.ht1;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Spinner EnrollsSpinner;
    Button cancelEnroll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.enrolled, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        scrollableText = getView().findViewById(R.id.Scrollable);
        scrollableText.setMovementMethod(new ScrollingMovementMethod());
        EnrollsSpinner = view.findViewById(R.id.RemoveEnrollspinner_enrolled);
        cancelEnroll = view.findViewById(R.id.bRemoveEnroll_enrolled);
        printAllUsersEnrolls();
        updateEnrolledSpinner();
        cancelEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelSelectedEnroll();
                updateEnrolledSpinner();
                printAllUsersEnrolls();
            }
        });
    }

    private void updateEnrolledSpinner() {
        List<String> spinnerList = getUsersEnrolls();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getView().getContext(),R.layout.support_simple_spinner_dropdown_item,spinnerList);
        EnrollsSpinner.setAdapter(adapter);
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

    public List<String> getUsersEnrolls() {
        List<String> UsersEnrolls = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'kk:mm");
        enrollList = SqlManager.getEnrollsFromDatabase();
        ReservtionToEnrollList = getAllReservation();
        for (Enroll enroll : enrollList) {
            if (enroll.getUserUUID() == User.getCurrentUser().getUUID()) {
                for (Reservation reservation : ReservtionToEnrollList) {
                    if (enroll.getReserveID() == reservation.getUUID()) {
                        reservation.getAttenderList(reservation);
                        String an_enroll = reservation.getSporthall().getName() + ", " + format.format(reservation.getStartDate().getTime()) + ", " + reservation.getSport() + ", " + reservation.getAttenderAmount();
                        UsersEnrolls.add(an_enroll);
                    }
                }
            }
        }
        return UsersEnrolls;
    }

    public void printAllUsersEnrolls() {
        scrollableText.setText("");
        for (int i=0; i<getUsersEnrolls().size(); i++) {
            scrollableText.append(getUsersEnrolls().get(i) + "\n");
        }
    }

    public void cancelSelectedEnroll() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'kk:mm");
        enrollList = SqlManager.getEnrollsFromDatabase();
        ReservtionToEnrollList = getAllReservation();
        for (Enroll enroll : enrollList) {
            if (enroll.getUserUUID() == User.getCurrentUser().getUUID()) {
                for (Reservation reservation : ReservtionToEnrollList) {
                    if (enroll.getReserveID() == reservation.getUUID()) {
                        reservation.getAttenderList(reservation);
                        String an_enroll = reservation.getSporthall().getName() + ", " + format.format(reservation.getStartDate().getTime()) + ", " + reservation.getSport() + ", " + reservation.getAttenderAmount();
                        if (an_enroll.equals(EnrollsSpinner.getSelectedItem().toString())) {
                            SqlManager.SQLenrolls.removeEnrollsByID(Integer.toString(enroll.getEnrollID()), Integer.toString(User.getCurrentUser().getUUID()));
                            System.out.println("nyt poistetaan: " + an_enroll + enroll.getEnrollID());
                        }
                    }
                }
            }
        }
    }
}
