package com.example.ht1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateEventFragment extends Fragment {

    private Spinner sporthallSpinner;
    private EditText sportNameEdit;
    private Button setStartCalBut;
    private EditText setStartClock;
    private TextView completeStartCalText;
    private EditText setDurationEdit;
    private TextView completeEndCalText;
    private EditText setMaxParticipantEdit;
    private EditText setReoccuranceEdit;
    private Button exportAllEventsCSVButton;
    private Switch activeSwitch;
    private Button createEventButton;
    private TextView isReservationPossibleText;

    private Context context;
    private Sporthall selectedSporthall;

    private List<String> spinnerList;

    final Calendar startCalendar = Calendar.getInstance();
    Calendar endCalendar;

    SimpleDateFormat formatDMY;
    SimpleDateFormat formatFull;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.createevent, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        context = this.getContext();
        formatDMY = new SimpleDateFormat("yyyy.MM.dd");
        formatFull = new SimpleDateFormat("yyyy.MM.dd  kk:mm");

        sporthallSpinner = getView().findViewById(R.id.sporthallSpinner_createevent);
        sportNameEdit = view.findViewById(R.id.eSetSportName_create);
        setStartCalBut = view.findViewById(R.id.bSetStartTime_create);
        setStartClock = view.findViewById(R.id.eSetStartTime);
        completeStartCalText = view.findViewById(R.id.CompleteStartDate_create);
        setDurationEdit = view.findViewById(R.id.eSetDurationHours_create);
        completeEndCalText = view.findViewById(R.id.CompleteEndDate_create);
        setMaxParticipantEdit = view.findViewById(R.id.eSetMaximumPart_create);
        setReoccuranceEdit = view.findViewById(R.id.eSetReoccuring_create);
        exportAllEventsCSVButton = view.findViewById(R.id.bExportToCSV_create);
        activeSwitch = view.findViewById(R.id.sRecurring_create);
        createEventButton = view.findViewById(R.id.bCreateEvent_create);
        isReservationPossibleText = view.findViewById(R.id.tIsReservationPossible_create);


        sporthallSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateAll(); // If sporthall spinner is touched all will update inside this fragment
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //asd
            }
        });

        final DatePickerDialog.OnDateSetListener date_datepicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                startCalendar.set(Calendar.YEAR, i);
                startCalendar.set(Calendar.MONTH, i1);
                startCalendar.set(Calendar.DAY_OF_MONTH, i2);
                updateAll();
            }
        };
        setStartCalBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context, date_datepicker, startCalendar.get(Calendar.YEAR),
                        startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        setDurationEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateAll();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateAll();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateAll();
            }
        });

        setStartClock.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateAll();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                updateAll();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                updateAll();
            }
        });

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEventMethod();
                setStartClock.setText(setStartClock.getText());
            }
        });

        updateSporthallSpinner();
        updateAll();
        exportAllEventsCSVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONManager CSV = new JSONManager(getActivity());
                //TODO CSV.saveReservationsCSV();
            }
        });
        updateSporthallSpinner();
    }


    private void updateAll() {
        // Method that updates texts whenever it is called
        setHoursToStartDate();
        setStartCalendar();
        setEndCalendar();
        checkReservationPossible(sporthallSpinner.getSelectedItemPosition());
        //updateSporthallSpinner();
    }


    // Checks if given time has no overlapping reservations
    // changes the color and content of the bottom text according to it
    private void checkReservationPossible(int index) {
        selectedSporthall = getSporthallFromSpinner(index);
        if (ReservationManager.isTimeSlotReserved(selectedSporthall, startCalendar, endCalendar)) {
            isReservationPossibleText.setText("Time is available!");
            isReservationPossibleText.setTextColor(Color.parseColor("#45f542")); // GREEN
            //Log.d("CREATE", "Reservation possible");
        }else if (selectedSporthall.getDisabled()){
            isReservationPossibleText.setText("Hall is not available");
            isReservationPossibleText.setTextColor(Color.parseColor("#f54242")); // RED
        }
        else {
            isReservationPossibleText.setText("Time is taken!");
            isReservationPossibleText.setTextColor(Color.parseColor("#f54242")); // RED
            //Log.d("CREATE", "Reservation NOT possible");
        }
    }


    // sets the hours and minutes to match the user inputfield
    private void setHoursToStartDate() {
        String clockText = setStartClock.getText().toString();
        //Log.d("CREATE", "ClockText: " + clockText);
        //Log.d("CREATE", "TextLength: " + clockText.length());
        if (!clockText.isEmpty()) {
            if (clockText.length() == 5) { // 5 characters
                String[] splitted = clockText.split(":");
                //Log.d("CREATE", "Split lenght: " + splitted.length);
                if (splitted.length == 2) {
                    int hours = Integer.parseInt(splitted[0]);
                    int minutes = Integer.parseInt(splitted[1]);
                    startCalendar.set(Calendar.HOUR_OF_DAY, hours);
                    startCalendar.set(Calendar.MINUTE, minutes);
                    Log.d("CREATE", "Time: " + hours + ":" + minutes);
                }
            }
        }
    }


    // When the create event button is pressed
    private void createEventMethod() {
        if (isGivenDataCorrect()) { // Calls to check if all the necessary info is given
            if (selectedSporthall != null) { // Just to be sure check that the sporthall exists
                if (startCalendar.after(Calendar.getInstance())) { // Checks if the desired start date is after the current date
                    if (ReservationManager.isTimeSlotReserved(selectedSporthall, startCalendar, endCalendar)) { // Calls method for final check if timeslot is free
                        // TODO Add system to and reoccuring events ReservationManager.addNewWeeklyReservation()
                        String sportName = sportNameEdit.getText().toString();
                        int duration = Integer.parseInt(setDurationEdit.getText().toString());
                        int maxPart = Integer.parseInt(setMaxParticipantEdit.getText().toString());

                        // call to reservationmanager to add a new reservation
                        ReservationManager.addNewReservation(User.getCurrentUser(), selectedSporthall, sportName, startCalendar, duration, maxPart, 0);
                        Toast.makeText(getActivity(), "Reservation created!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(getActivity(), "Time is taken!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(getActivity(), "Illegal start time", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
        Toast.makeText(getActivity(), "Fill all the fields!", Toast.LENGTH_SHORT).show();
    }


    private boolean isGivenDataCorrect() { // checks if all the necessary info is given and properly
        if (!sportNameEdit.getText().toString().isEmpty()) {
            if (!setMaxParticipantEdit.getText().toString().isEmpty()) {
                if (!setDurationEdit.getText().toString().isEmpty()) {
                    if (Integer.parseInt(setDurationEdit.getText().toString()) > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private void updateSporthallSpinner() {
        spinnerList = new ArrayList<>();
        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            if (!sporthall.getDisabled()) {
                spinnerList.add(sporthall.getName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getView().getContext(),R.layout.support_simple_spinner_dropdown_item,spinnerList);
        sporthallSpinner.setAdapter(adapter);
    }


    private Sporthall getSporthallFromSpinner(int index) {

        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            if (!sporthall.getDisabled()) {
                if (sporthall.getName().equalsIgnoreCase(spinnerList.get(index))) {
                    return sporthall;
                }
            }
        }
        return null;
    }


    private void setStartCalendar() {
        completeStartCalText.setText(formatDMY.format(startCalendar.getTime()));
    }


    private void setEndCalendar() {
        endCalendar = (Calendar) startCalendar.clone();
        String innerText = setDurationEdit.getText().toString();
        if (!innerText.isEmpty()) {
            int duration = Integer.parseInt(innerText);
            endCalendar.add(Calendar.HOUR_OF_DAY, duration);
        }
        completeEndCalText.setText(formatFull.format(endCalendar.getTime()));
    }
}
