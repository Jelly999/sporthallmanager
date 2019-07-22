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
    private String selectedEventID;
    private Calendar eventStart;
    private Calendar evenEnd;
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
        changeSporthall = view.findViewById(R.id.eChangeSporthall_edit);
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
    private void saveChanges(){
        String sport = setSport.toString();
        String changehall = changeSporthall.toString();
        String duration = editDuration.toString();
        String maxparticipants = editMaxParticipants.toString();
        int hall = 0;
        int dur = 0;
        int eventID;
        if (sport.length() > 0){
            //change sport name
            toast("name in use");}
        if (changehall.length() > 0){
            hall = 1;
            toast("hall unavailable");}
        if (duration.length() > 0){
            dur = 1;
        toast("length is overlapping");}
        if (maxparticipants.length() > 0){
            //check and edit event
            toast("too many attenders");}
        if (hall == 0 && dur == 0){
            int duration2 = Integer.parseInt(duration);
            //Date eventEnd = addHoursToJavaUtilDate(eventStart,duration2);
            //ReservationManager.isTimeSlotReserved(changehall, eventStart, evenEnd)
        }else if (hall == 1){

        }else if (dur == 1){

        }
    }

    private void toast(String msg){
        Context context = getActivity();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text, duration);
        toast.show();
    }
    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, hours); // adds one hour
        return cal.getTime(); // returns new date object, one hour in the future
    }
    private void updateEditeventSpinner() {
        spinnerList = (ArrayList) getReservations();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getView().getContext(),R.layout.support_simple_spinner_dropdown_item,spinnerList);
        eventSpinner.setAdapter(adapter);
    }
    public List<String> getReservations(){
        //TODO get reservation and display them
        List<String> reservations = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd kk:mm");
        int user_uuid;
        user_uuid = User.getCurrentUser().getUUID();
        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            sporthall.updateReservationsFromSQL();
            for (Reservation reservation : sporthall.getReservations()) {
                if (reservation.getOwner().getUUID() == user_uuid) {
                    reservations.add(reservation.getSporthall().getName() + ", " + format.format(reservation.getStartDate().getTime()) + ", " + reservation.getSport());
                }
            }
        }
        return reservations;
    }
}
