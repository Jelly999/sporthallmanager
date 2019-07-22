package com.example.ht1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventEditFragment extends Fragment {
    private Spinner eventSpinner;
    private EditText setSport;
    private EditText changeSporthall;
    private EditText editDuration;
    private EditText editMaxParticipants;
    private Button deleteReservationB;
    private Button saveEdits;
    private ArrayList spinnerList;
    private ArrayList spinnerIDList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.eventedit, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        eventSpinner = view.findViewById(R.id.Eventspinner_edit);
        setSport = view.findViewById(R.id.eSetEventSport_edit);
        editDuration = view.findViewById(R.id.eEditEventDuration_edit);
        editMaxParticipants = view.findViewById(R.id.eEditMAXParticipants_edit);
        deleteReservationB = view.findViewById(R.id.bRemoveSelectReservation_edit);
        saveEdits = view.findViewById(R.id.bSave_edit);
        deleteReservationB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteReservation();
            }
        });
        saveEdits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
        updateEditeventSpinner();
    }
    private void deleteReservation(){
        //TODO Remove reservation from database
    }
    private void saveChanges() {
        int pos = eventSpinner.getSelectedItemPosition();
        pos =+ 1;
        String sport = "'" + setSport.getText().toString() + "'";
        String duration = editDuration.getText().toString();
        String maxparticipants = editMaxParticipants.getText().toString();
        String Uuid = (String) spinnerIDList.get(0*pos);
        if (sport.length() > 0 ){
            SqlManager.SQLreservation.updateRow(Uuid,"sport", sport);
        }
        if (duration.length() > 0){
            //TODO collision check!
            SqlManager.SQLreservation.updateRow(Uuid,"duration", duration);
        }
        if (maxparticipants.length() > 0){
            //TODO ammount check
            SqlManager.SQLreservation.updateRow(Uuid,"maxparticipants", maxparticipants);
        }
        updateEditeventSpinner();
    }
        private void toast(String msg){
        Context context = getActivity();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text, duration);
        toast.show();
    }

    private void updateEditeventSpinner() {
        spinnerList = (ArrayList) getReservations();
        spinnerIDList = (ArrayList) getReservationsID();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getView().getContext(),R.layout.support_simple_spinner_dropdown_item,spinnerList);
        eventSpinner.setAdapter(adapter);
    }
    public List<String> getReservations(){
        List<String> reservations = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd kk:mm");
        int user_uuid;
        user_uuid = User.getCurrentUser().getUUID();
        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            sporthall.updateReservationsFromSQL();
            for (Reservation reservation : sporthall.getReservations()) {
                if (reservation.getOwner().getUUID() == user_uuid) {
                    reservations.add(Integer.toString(reservation.getUUID())+": "+reservation.getSporthall().getName() + ": " + format.format(reservation.getStartDate().getTime()) + ": " + reservation.getSport()+": "+reservation.getMaxParticipants());
                }
            }
        }
        System.out.println(reservations);
        return reservations;
    }
    public List<String> getReservationsID(){
        List<String> reservations = new ArrayList<>();
        int user_uuid;
        user_uuid = User.getCurrentUser().getUUID();
        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            sporthall.updateReservationsFromSQL();
            for (Reservation reservation : sporthall.getReservations()) {
                if (reservation.getOwner().getUUID() == user_uuid) {
                    reservations.add(Integer.toString(reservation.getUUID()));
                }
            }
        }
        return reservations;
    }
}
